package befaster.solutions.CHK;

public class Item {

    private final String sku;
    private final Integer price;
    private final Multibuy multibuy;

    public Item(String sku, Integer price, Multibuy multibuy) {
        this.sku = sku;
        this.price = price;
        this.multibuy = multibuy;
    }

    public String getSku() {
        return sku;
    }

    public Integer getPrice() {
        return price;
    }

    public Multibuy getMultibuy() {
        return multibuy;
    }
}

