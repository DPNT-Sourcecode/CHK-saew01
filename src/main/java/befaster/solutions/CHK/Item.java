package befaster.solutions.CHK;

import com.google.common.collect.Lists;

import java.util.List;

public class Item {

    private final String sku;
    private final Integer price;
    private final List<Multibuy> multibuys;

    public Item(String sku, Integer price, List<Multibuy> multibuys) {
        this.sku = sku;
        this.price = price;
        this.multibuys = multibuys == null ? Lists.newArrayList() : multibuys;
    }

    public String getSku() {
        return sku;
    }

    public Integer getPrice() {
        return price;
    }

    public List<Multibuy> getMultibuys() {
        return multibuys;
    }
}

