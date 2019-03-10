package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

   private final Map<String, Item> pricingTable;

    public CheckoutSolution() {
        pricingTable = createItems();
    }

    private Map<String, Item> createItems() {
        Map<String, Item> itemList = new HashMap<>();
        itemList.put("A", new Item("A", 50, new Multibuy(3, 130)));
        itemList.put("B", new Item("B", 30, new Multibuy(2, 45)));
        itemList.put("C", new Item("C", 20, null));
        itemList.put("D", new Item("D", 15, null));
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
                if(isMultibuyApplicable(itemInfo, numberOfItemsRequested))
                {
                    Integer multiBuyThreshold = itemInfo.getMultibuy().getCount();
                    Integer numberOfMultibuys = numberOfItemsRequested / multiBuyThreshold;
                    Integer remainderFromMultibuys = numberOfItemsRequested % multiBuyThreshold;
                    total += itemInfo.getMultibuy().getPrice() * numberOfMultibuys;
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

    private boolean isMultibuyApplicable(Item itemInfo, Integer numberOfItemsRequested) {
        return itemInfo.getMultibuy() != null && numberOfItemsRequested >= itemInfo.getMultibuy().getCount();
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
