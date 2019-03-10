package befaster.solutions.CHK;

public class PriceReductionMultibuy extends Multibuy {
    private final Integer price;

    public PriceReductionMultibuy(Integer count, Integer price) {
        super(count);
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

}

