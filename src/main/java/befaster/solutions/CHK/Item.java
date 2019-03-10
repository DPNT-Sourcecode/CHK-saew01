package befaster.solutions.CHK;

public class Item {

    private String sku;
    private Integer price;

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

