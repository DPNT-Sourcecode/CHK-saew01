package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckoutSolution {

   private final Map<String, Item> pricingTable;

    public CheckoutSolution() {
        pricingTable = createItems();
    }

    private Map<String, Item> createItems() {
        Map<String, Item> itemList = new HashMap<>();
        itemList.put("A", new Item("A", 50));
        itemList.put("B", new Item("B", 30));
        itemList.put("C", new Item("C", 20));
        itemList.put("D", new Item("D", 15));
        return itemList;
    }

    public Integer checkout(String skus) {

        try {
            Map<String, Integer> itemsInBasket = calculateItemsRequested(skus);

            //Do multi pricing bit here, will keep simple for now
            //Do normal pricing first
            itemsInBasket.entrySet().for
        } catch(IllegalArgumentException e) {
            //Return -1 for unknown skus
            return -1;
        }


    }

    private  Map<String, Integer> calculateItemsRequested(String skus) {
        Map<String, Integer> itemTracker = new HashMap<>();
        String[] skuArray = skus.split(",");
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




