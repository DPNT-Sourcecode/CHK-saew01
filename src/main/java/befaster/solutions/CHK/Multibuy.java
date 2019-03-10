package befaster.solutions.CHK;

import java.util.List;

public abstract class Multibuy {
    private final List<String> skusForMultibuy;
    private final Integer count;

    public Multibuy(List<String> skusForMultibuy, Integer count) {
        this.skusForMultibuy = skusForMultibuy;
        this.count = count;
    }

    public List<String> getSkusForMultibuy() {
        return skusForMultibuy;
    }

    public Integer getCount() {
        return count;
    }

}
