package befaster.solutions.CHK;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

            Integer total = applyMultibuys(itemsInBasket);

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
            Optional<Multibuy> applicableMultibuy = multibuys.stream()
                    .filter(multibuy -> multibuy.getSkusForMultibuy().contains(itemInfo.getSku())
                        && multibuy.getSkusForMultibuy().size() > 1)
                    .findFirst();
            if(applicableMultibuy.isPresent())
            {
                total = checkGroupMultibuy(itemInBasket, applicableMultibuy, itemsInBasket);
            }
        }
        return total;
    }

    private Integer checkGroupMultibuy(Map.Entry<String, Integer> itemInBasket, Multibuy applicableMultibuys, Map<String, Integer> itemsInBasket) {

        List<Item> items = itemsInBasket.entrySet().stream()
                .filter(entry -> applicableMultibuys.getSkusForMultibuy().contains(entry.getKey())).map
                .collect(Collectors.toList())

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

