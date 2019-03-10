package befaster.solutions.CHK;

import java.util.List;

public class PriceReductionMultibuy extends Multibuy {
    private final Integer price;

    public PriceReductionMultibuy(List<String> skuItemsForMultibuy, Integer count, Integer price) {
        super(skuItemsForMultibuy, count);
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

}



