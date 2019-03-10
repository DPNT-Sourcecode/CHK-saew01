package befaster.solutions.CHK;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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

    public Map<List<String>, Multibuy> createMultibuys() {
        Map<List<String>, Multibuy> multibuys = Maps.newHashMap();
        List<String> commonMultiBuySkus = Lists.newArrayList("S", "T", "X", "Y", "Z");
        multibuys.put(Lists.newArrayList("A"), new PriceReductionMultibuy(3, 130));
        multibuys.put(Lists.newArrayList("A"), new PriceReductionMultibuy(,5, 200));
        multibuys.put(Lists.newArrayList("B"),new PriceReductionMultibuy(2, 45));
        multibuys.put(Lists.newArrayList("E"),new FreeItemMultibuy(2, "B"));
        multibuys.put(Lists.newArrayList("F"),new PriceReductionMultibuy(3, 20));
        multibuys.put(Lists.newArrayList("H"),new PriceReductionMultibuy(5, 45));
        multibuys.put(Lists.newArrayList("H"),new PriceReductionMultibuy(10, 80));
        multibuys.put(Lists.newArrayList("K"),new PriceReductionMultibuy(2, 120));
        multibuys.put(Lists.newArrayList("N"),new FreeItemMultibuy(3, "M"));
        multibuys.put(Lists.newArrayList("P"),new PriceReductionMultibuy(5, 200));
        multibuys.put(Lists.newArrayList("Q"),new PriceReductionMultibuy(3, 80));
        multibuys.put(Lists.newArrayList("R"),new FreeItemMultibuy(5, "Q"));
        multibuys.put(commonMultiBuySkus, new PriceReductionMultibuy(3, 45));
        multibuys.put(Lists.newArrayList("U"),new PriceReductionMultibuy(4, 120));
        multibuys.put(Lists.newArrayList("V"),new PriceReductionMultibuy(2, 90));
        multibuys.put(Lists.newArrayList("V"),new PriceReductionMultibuy(3, 130));
        return multibuys;
    }
}


