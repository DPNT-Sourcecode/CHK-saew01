package befaster.solutions.CHK;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckoutSolutionTest {
    private CheckoutSolution checkoutSolution;

    @Before
    public void setUp() {
        checkoutSolution = new CheckoutSolution();
    }
    @Test
    public void checkout_noItem() {
        Integer actual = this.checkoutSolution.checkout("");
        Assert.assertEquals(Integer.valueOf(0), actual);

    }
    @Test
    public void checkout_singleItem() {
        Integer actual = this.checkoutSolution.checkout("A");
        Assert.assertEquals(Integer.valueOf(50), actual);
    }

    @Test
    public void checkout_comboOfItem() {
        Integer actual = this.checkoutSolution.checkout("ABCD");
        Assert.assertEquals(Integer.valueOf(115), actual);
    }

    @Test
    public void checkout_badInput() {
        Integer actual = this.checkoutSolution.checkout("AxA");
        Assert.assertEquals(Integer.valueOf(-1), actual);
    }


    @Test
    public void checkout_singleItem_unknown() {
        Integer actual = this.checkoutSolution.checkout("a");
        Assert.assertEquals(Integer.valueOf(-1), actual);

    }

    @Test
    public void checkout_shouldReturnNegativeOneOnIllegalValues() {
        Integer actual = this.checkoutSolution.checkout("This is in valid");
        Assert.assertEquals("Expected -1 to be the result on illegal input", Integer.valueOf(-1), actual);
    }

    @Test
    public void checkout_shouldCalculateBasketCorrectly() {
        Integer actual = this.checkoutSolution.checkout("ABCD");
        Assert.assertEquals(Integer.valueOf(115), actual);
    }

    @Test
    public void checkout_shouldCalculateJustShortOfMultibuyCorrectly() {
        Integer actual = this.checkoutSolution.checkout("AA");
        Assert.assertEquals(Integer.valueOf(100), actual);
    }

    @Test
    public void checkout_shouldCalculateMultibuyCorrectly() {
        Integer actual = this.checkoutSolution.checkout("AAA");
        Assert.assertEquals(Integer.valueOf(130), actual);
    }

    @Test
    public void checkout_shouldCalculateMultibuyWithSinglesCorrectly() {
        Integer actual = this.checkoutSolution.checkout("AAAA");
        Assert.assertEquals(Integer.valueOf(180), actual);
    }

    @Test
    public void checkout_shouldCalculateMultipleMultibuysCorrectly() {

        //TestNG style dataprovider
        Object[][] dataProvider = new Object[][] {
                new Object[]{"AAAAAAAAAA", 400},
                new Object[]{"AAAAAAAA", 330},
                new Object[]{"AAAAAAAAA", 380},
                new Object[]{"AAAAAAAAAA", 390}
        };
        for (Object[] input : dataProvider) {
            String skus = (String)input[0];
            Integer expected = (Integer)input[1];
            Integer actual = this.checkoutSolution.checkout(skus);
            Assert.assertEquals(String.format("Input %s did not recieve expected value", skus) ,expected, actual);
        }

    }


    @Test
    public void checkout_shouldCalculateMultibuyWithOtherItemsCorrectly() {
        Integer actual = this.checkoutSolution.checkout("BBAD");
        Assert.assertEquals(Integer.valueOf(110), actual);
    }

    @Test
    private void checkout_shouldCalcualteMultibuyWhichProvideFreeProducts_freeProductNotPresent() {
        Integer actual = this.checkoutSolution.checkout("EE");
        Assert.assertEquals(Integer.valueOf(80), actual);
    }

    @Test
    public void checkout_shouldCalcualteMultibuyWhichProvideFreeProducts_freeProductPresent() {
        Integer actual = this.checkoutSolution.checkout("EEB");
        Assert.assertEquals(Integer.valueOf(80), actual);
    }

    @Test
    public void checkout_shouldCalcualteMultibuyWhichProvideFreeProducts_twoProductsFree() {
        Integer actual = this.checkoutSolution.checkout("EEEEBBB");
        Assert.assertEquals(Integer.valueOf(190), actual);
    }

    @Test
    public void checkout_shouldCalcualteMultibuyWhichProvideFreeProducts_twoProductsFree() {
        Integer actual = this.checkoutSolution.checkout("EE");
        Assert.assertEquals(Integer.valueOf(190), actual);
    }
}

