package befaster.solutions.CHK;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CheckoutSolution {

   private final Map<String, Item> pricingTable;

    public CheckoutSolution() {
        pricingTable = createItems();
    }

    private Map<String, Item> createItems() {
        Map<String, Item> itemList = new HashMap<>();
        itemList.put("A", new Item("A", 50,
                Lists.newArrayList(new PriceReductionMultibuy(3, 130),
                        new PriceReductionMultibuy(5, 200))));
        itemList.put("B", new Item("B", 30,
                Lists.newArrayList(new PriceReductionMultibuy(2, 45))));
        itemList.put("C", new Item("C", 20, null));
        itemList.put("D", new Item("D", 15, null));
        itemList.put("E", new Item("E", 40,
                Lists.newArrayList(new FreeItemMultibuy(2, "B"))));
        return itemList;
    }

    public Integer checkout(String skus) {
        Integer total = 0;
        if(skus.length() == 0) {
            return 0;
        }

        try {
            Map<String, Integer> itemsInBasket = calculateItemsRequested(skus);

            for (Map.Entry<String, Integer> itemInBasket : itemsInBasket.entrySet()) {
                Item itemInfo = pricingTable.get(itemInBasket.getKey());
                Integer numberOfItemsRequested = itemInBasket.getValue();

                //apply multibuy first, then calculate prices?
                //can't seperate out as before now
                applyMultibuys();


                if(isMultibuyApplicable(itemInfo))
                {
                    Integer multiBuyThreshold = itemInfo.getMultibuys().getCount();
                    Integer numberOfMultibuys = numberOfItemsRequested / multiBuyThreshold;
                    Integer remainderFromMultibuys = numberOfItemsRequested % multiBuyThreshold;
                    total += itemInfo.getMultibuys().getPrice() * numberOfMultibuys;
                    total += itemInfo.getPrice() * remainderFromMultibuys;
                } else {
                    total += itemInfo.getPrice() * numberOfItemsRequested;
                }
            }
            return total;
        } catch(IllegalArgumentException e) {
            //Return -1 for unknown skus
            return -1;
        }
    }

    private void applyMultibuys(Map<String, Integer> itemsInBasket, Integer total) {
        for (Map.Entry<String, Integer> itemInBasket : itemsInBasket.entrySet()) {
            Item itemInfo = pricingTable.get(itemInBasket.getKey());
            if(itemInfo.getMultibuys().size() > 0)
            {
                Integer requestedItems = itemInBasket.getValue();

                for(Multibuy multibuy : itemInfo.getMultibuys()) {

                }
            }
        }
    }

    private Optional<Multibuy> getBestApplicablePriceMultibuy(List<Multibuy> multibuyList) {
        Optional<PriceReductionMultibuy> bestMultibuy = Optional.empty();
        for (Multibuy multibuy : multibuyList) {
            if(!bestMultibuy.isPresent() || bestMultibuy.get().get)
        }
    }

    private boolean isMultibuyApplicable(Item itemInfo) {

        return itemInfo.getMultibuys().size() > 0;
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

