package befaster.solutions.CHK;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Map;

public class PricingData {

    protected static Map<String, Item> createItems() {
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
        itemList.put("F", new Item("F", 10,
                Lists.newArrayList(new PriceReductionMultibuy(3, 20))));
        itemList.put("G", new Item("G", 20, null));
        itemList.put("H", new Item("H", 10,
                Lists.newArrayList(new PriceReductionMultibuy(5, 45),
                new PriceReductionMultibuy(10, 80))));
        itemList.put("I", new Item("I", 35, null));
        itemList.put("J", new Item("J", 60, null));
        itemList.put("K", new Item("K", 80,
                Lists.newArrayList(new PriceReductionMultibuy(2, 150))));
        itemList.put("L", new Item("L", 90, null));
        itemList.put("M", new Item("M", 15, null));
        itemList.put("N", new Item("N", 40,
                Lists.newArrayList(new FreeItemMultibuy(3, "M"))));
        itemList.put("O", new Item("O", 10, null));
        itemList.put("P", new Item("P", 50,
                Lists.newArrayList(new PriceReductionMultibuy(5, 200))));
        itemList.put("Q", new Item("Q", 30,
                Lists.newArrayList(new PriceReductionMultibuy(3, 80))));
        itemList.put("R", new Item("R", 50,
                Lists.newArrayList(new FreeItemMultibuy(3, "Q"))));
        itemList.put("S", new Item("S", 30, null));
        itemList.put("T", new Item("T", 20, null));
        itemList.put("U", new Item("U", 40,
                Lists.newArrayList(new PriceReductionMultibuy(4, 120))));
        itemList.put("V", new Item("V", 50,
                Lists.newArrayList(new PriceReductionMultibuy(2, 90),
                        new PriceReductionMultibuy(3, 130))));
        itemList.put("W", new Item("W", 20, null));
        itemList.put("X", new Item("X", 90, null));
        itemList.put("Y", new Item("Y", 10, null));
        itemList.put("Z", new Item("Z", 50, null));

        return itemList;
    }
}
