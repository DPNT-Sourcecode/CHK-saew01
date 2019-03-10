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

        skus.split(",")

    }
}


