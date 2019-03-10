package befaster.solutions.CHK;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class CheckoutSolution {

   private final Map<String, Item> pricingTable;
   private final List<Multibuy> multibuys;

    public CheckoutSolution() {
        pricingTable = PricingData.createItems();
        multibuys = PricingData.createMultibuys();
    }

    public Integer checkout(String skus) {
        if(skus.length() == 0) {
            return 0;
        }

        try {
            Map<String, Integer> itemsInBasket = calculateItemsRequested(skus);
            Integer total = applyGroupMultibuy(itemsInBasket);
            total += applyMultibuys(itemsInBasket);

            for (Map.Entry<String, Integer> itemInBasket : itemsInBasket.entrySet()) {
                Item itemInfo = pricingTable.get(itemInBasket.getKey());
                Integer numberOfItemsRequested = itemInBasket.getValue();

                total += itemInfo.getPrice() * numberOfItemsRequested;
            }
            return total;
        } catch(IllegalArgumentException e) {
            //Return -1 for unknown skus
            return -1;
        }
    }

    private Integer applyGroupMultibuy(Map<String, Integer> itemsInBasket) {
        Integer total = 0;

        for (Map.Entry<String, Integer> itemInBasket : itemsInBasket.entrySet()) {
            Item itemInfo = pricingTable.get(itemInBasket.getKey());
            Optional<PriceReductionMultibuy> applicableMultibuy = multibuys.stream()
                    .filter(multibuy -> multibuy instanceof PriceReductionMultibuy
                            && multibuy.getSkusForMultibuy().contains(itemInfo.getSku())
                            && multibuy.getSkusForMultibuy().size() > 1)
                    .map(multibuy -> (PriceReductionMultibuy) multibuy)
                    .findFirst();

            if(applicableMultibuy.isPresent())
            {
                total = checkGroupMultibuy(applicableMultibuy.get(), itemsInBasket);
            }
        }
        return total;
    }

    private Integer checkGroupMultibuy(PriceReductionMultibuy applicableMultibuy, Map<String, Integer> itemsInBasket) {

       List<Item> items =  itemsInBasket.entrySet().stream()
                .filter(entry -> applicableMultibuy.getSkusForMultibuy().contains(entry.getKey()))
                .map(entry -> this.pricingTable.get(entry.getKey()))
                .sorted((o1, o2) -> {
                            if (o1.getPrice().equals(o2.getPrice())) {
                                return 0;
                            } else if (o1.getPrice() > o2.getPrice()) {
                                return -1;
                            } else {
                                return 1;
                            }
                        }
                )
                .collect(Collectors.toList());

       Integer numberOfRequestedItems = items.size();
       Integer total = 0;
       while(numberOfRequestedItems >= applicableMultibuy.getCount()) {
           //find x most expensive items
           final List<Item> expensiveItems = Lists.newArrayList();
           for (Integer i = 0; i < applicableMultibuy.getCount(); i++) {
                   expensiveItems.add(items.remove(0));
           }

           total += applicableMultibuy.getPrice();
           for (Item expensiveItem : expensiveItems) {
               Integer numberInBasket = itemsInBasket.get(expensiveItem.getSku());
               itemsInBasket.put(expensiveItem.getSku(), numberInBasket -1);
           }
           numberOfRequestedItems = items.size();
       }

       return total;

    }

    private Integer applyMultibuys(Map<String, Integer> itemsInBasket) {
        Integer total = 0;
        for (Map.Entry<String, Integer> itemInBasket : itemsInBasket.entrySet()) {
            Item itemInfo = pricingTable.get(itemInBasket.getKey());
            List<Multibuy> applicableMultibuys = multibuys.stream()
                    .filter(multibuy -> multibuy.getSkusForMultibuy().contains(itemInfo.getSku()))
                    .collect(Collectors.toList());
            if(applicableMultibuys.size() > 0)
            {
                applyFreeItemMultibuy(itemInBasket, applicableMultibuys, itemsInBasket);
            }
        }

        for (Map.Entry<String, Integer> itemInBasket : itemsInBasket.entrySet()) {
            Item itemInfo = pricingTable.get(itemInBasket.getKey());
            List<Multibuy> applicableMultibuys = multibuys.stream()
                    .filter(multibuy -> multibuy.getSkusForMultibuy().contains(itemInfo.getSku()))
                    .collect(Collectors.toList());
            if(applicableMultibuys.size() > 0)
            {
                total = applyPriceMultibuy(total, itemInBasket, applicableMultibuys);
            }
        }

        return total;
    }

    private void applyFreeItemMultibuy(Map.Entry<String, Integer> itemInBasket, List<Multibuy> applicableMultibuys, Map<String, Integer> itemsInBasket) {
        Integer requestedItems = itemInBasket.getValue();

        if(applicableMultibuys.get(0) instanceof  FreeItemMultibuy) {
            FreeItemMultibuy multibuy =  (FreeItemMultibuy)applicableMultibuys.get(0);
            while(requestedItems >= multibuy.getCount()) {
                requestedItems -= multibuy.getCount();
                Integer currentValueInBasket = itemsInBasket.get(multibuy.getSku());
                if(itemsInBasket.containsKey(multibuy.getSku()) && currentValueInBasket > 0) {
                    itemsInBasket.put(multibuy.getSku(), currentValueInBasket - 1);
                }
            }
        }

    }

    private Integer applyPriceMultibuy(Integer total, Map.Entry<String, Integer> itemInBasket, List<Multibuy> applicableMultibuys) {
        Integer requestedItems = itemInBasket.getValue();

        Optional<PriceReductionMultibuy> priceReductionMultibuy =
                getBestApplicablePriceMultibuy(applicableMultibuys, requestedItems);
        while(priceReductionMultibuy.isPresent()) {
            requestedItems -= priceReductionMultibuy.get().getCount();
            itemInBasket.setValue(requestedItems);
            total += priceReductionMultibuy.get().getPrice();
            priceReductionMultibuy = getBestApplicablePriceMultibuy(applicableMultibuys, requestedItems);
        }
        return total;
    }

    private Optional<PriceReductionMultibuy> getBestApplicablePriceMultibuy(List<Multibuy> multibuyList, Integer requestedNumber) {
        Optional<PriceReductionMultibuy> bestMultibuy = Optional.empty();
        for (Multibuy multibuy : multibuyList) {
            if(multibuy instanceof PriceReductionMultibuy &&
                    requestedNumber >= multibuy.getCount() && (!bestMultibuy.isPresent()
                    || bestMultibuy.get().getCount() < multibuy.getCount())) {
                bestMultibuy = Optional.of((PriceReductionMultibuy) multibuy);
            }
        }
        return bestMultibuy;
    }

    private Map<String, Integer> calculateItemsRequested(String skus) {
        Map<String, Integer> itemTracker = new HashMap<>();
        String[] skuArray = skus.split("");
        for (String sku : skuArray) {
            if(!pricingTable.containsKey(sku)) {
                throw new IllegalArgumentException(String.format("Unexpected sku %s", sku));
            }

            if(itemTracker.containsKey(sku)) {
                Integer currentCount = itemTracker.get(sku);
                itemTracker.put(sku, currentCount + 1);
            } else {
                itemTracker.put(sku, 1);
            }
        }
        return  itemTracker;
    }
}



