package befaster.solutions.CHK;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PricingData {

    protected static Map<String, Item> createItems() {
        Map<String, Item> itemList = new HashMap<>();
        itemList.put("A", new Item("A", 50));
        itemList.put("B", new Item("B", 30));
        itemList.put("C", new Item("C", 20));
        itemList.put("D", new Item("D", 15));
        itemList.put("E", new Item("E", 40));
        itemList.put("F", new Item("F", 10));
        itemList.put("G", new Item("G", 20));
        itemList.put("H", new Item("H", 10));
        itemList.put("I", new Item("I", 35));
        itemList.put("J", new Item("J", 60));
        itemList.put("K", new Item("K", 70));
        itemList.put("L", new Item("L", 90));
        itemList.put("M", new Item("M", 15));
        itemList.put("N", new Item("N", 40));
        itemList.put("O", new Item("O", 10));
        itemList.put("P", new Item("P", 50));
        itemList.put("Q", new Item("Q", 30));
        itemList.put("R", new Item("R", 50));
        itemList.put("S", new Item("S", 20));
        itemList.put("T", new Item("T", 20));
        itemList.put("U", new Item("U", 40));
        itemList.put("V", new Item("V", 50));
        itemList.put("W", new Item("W", 20));
        itemList.put("X", new Item("X", 17));
        itemList.put("Y", new Item("Y", 20));
        itemList.put("Z", new Item("Z", 21));

        return itemList;
    }

    public static List<Multibuy> createMultibuys() {
        List<Multibuy> multibuys = Lists.newArrayList();
        List<String> commonMultiBuySkus = Lists.newArrayList("S", "T", "X", "Y", "Z");
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("A"),3, 130));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("A"),5, 200));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("B"),2, 45));
        multibuys.add(new FreeItemMultibuy(Lists.newArrayList("E"),2, "B"));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("F"),3, 20));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("H"),5, 45));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("H"),10, 80));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("K"),2, 120));
        multibuys.add(new FreeItemMultibuy(Lists.newArrayList("N"),3, "M"));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("P"),5, 200));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("Q"),3, 80));
        multibuys.add(new FreeItemMultibuy(Lists.newArrayList("R"),3, "Q"));
        multibuys.add(new PriceReductionMultibuy(commonMultiBuySkus,3, 45));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("U"),4, 120));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("V"),2, 90));
        multibuys.add(new PriceReductionMultibuy(Lists.newArrayList("V"),3, 130));
        return multibuys;
    }
}
