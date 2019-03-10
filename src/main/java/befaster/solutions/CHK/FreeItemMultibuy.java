package befaster.solutions.CHK;

public class FreeItemMultibuy extends Multibuy{
    private final String sku;

    public FreeItemMultibuy(Integer count, String sku) {
        super(count);
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }


}
