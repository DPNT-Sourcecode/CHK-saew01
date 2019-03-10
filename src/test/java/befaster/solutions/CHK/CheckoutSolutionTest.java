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

        //TestNG style dataprovider, but not quite. Not familiar with way to do in junit
        Object[][] dataProvider = new Object[][] {
                new Object[]{"AAAAAAA", 300}, //7 As
                new Object[]{"AAAAAAAA", 330}, //8 As
                new Object[]{"AAAAAAAAA", 380}, //9 As
                new Object[]{"AAAAAAAAAA", 400}, //10 As
                new Object[]{"FFF", 20},
                new Object[]{"FFFF", 30},
                new Object[]{"FFFFF", 40},
                new Object[]{"FFFFFF", 40},
                new Object[]{"HHHHH", 45},
                new Object[]{"HHHHHHHHHH", 80},
                new Object[]{"KK", 120},
                new Object[]{"PPPPP", 200},
                new Object[]{"QQQ", 80},
                new Object[]{"UUUU", 120},
                new Object[]{"VV", 90},
                new Object[]{"VVV", 130}
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
    public void checkout_shouldCalcualteMultibuyWhichProvideFreeProducts_freeProductNotPresent() {
        //TestNG style dataprovider, but not quite. Not familiar with way to do in junit
        Object[][] dataProvider = new Object[][] {
                new Object[]{"EE", 80},
                new Object[]{"EEB", 80},
                new Object[]{"EEEEBBB", 190},
                new Object[]{"NNNM", 120},
                new Object[]{"RRRQ", 150}
        };

        for (Object[] input : dataProvider) {
            String skus = (String)input[0];
            Integer expected = (Integer)input[1];
            Integer actual = this.checkoutSolution.checkout(skus);
            Assert.assertEquals(String.format("Input %s did not receive expected value", skus) ,expected, actual);
        }
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
    public void checkout_multipleDifferentProductsMultibuy() {
        Integer actual = this.checkoutSolution.checkout("SSTXYZZY");
        Assert.assertEquals(Integer.valueOf(127), actual);
    }
}
