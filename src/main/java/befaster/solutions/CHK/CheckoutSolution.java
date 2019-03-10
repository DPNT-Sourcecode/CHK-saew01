package befaster.solutions.CHK;

import befaster.runner.SolutionNotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

   private final Map<String, Item> pricingTable = new HashMap<>();

    public CheckoutSolution() {
        List<Item> items = createItems();
        items.stream().map
        pricingTable.
    }

    private List<Item> createItems() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("A", 50));
        itemList.add(new Item("B", 50));
        itemList.add(new Item("C", 50));
        itemList.add(new Item("D", 50));
        return itemList;
    }

    public Integer checkout(String skus) {
        throw new SolutionNotImplementedException();
    }
}

