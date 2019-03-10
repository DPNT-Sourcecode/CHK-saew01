package befaster.solutions.CHK;

import java.util.List;

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


