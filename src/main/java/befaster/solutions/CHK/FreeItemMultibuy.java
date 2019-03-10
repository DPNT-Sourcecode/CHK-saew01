package befaster.solutions.CHK;

import java.util.List;

public class FreeItemMultibuy extends Multibuy{
    private final String sku;

    public FreeItemMultibuy(List<String> skuItemsForMultibuy, Integer count, String sku) {
        super(skuItemsForMultibuy, count);
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }


}

