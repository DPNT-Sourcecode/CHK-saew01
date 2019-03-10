package befaster.solutions.CHK;

import com.google.common.collect.Lists;

import java.util.List;

public class Item {

    private final String sku;
    private final Integer price;

    public Item(String sku, Integer price) {
        this.sku = sku;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public Integer getPrice() {
        return price;
    }
}
