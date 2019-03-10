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
    public void checkout_shouldReturnNegativeOneOnIllegalValues() {
        Integer actual = this.checkoutSolution.checkout("This is in valid");
        Assert.assertEquals("Expected -1 to be the result on illegal input", Integer.valueOf(-1), actual);
    }

    @Test
    public void checkout_shouldCalculateBasketCorrectly() {
        Integer actual = this.checkoutSolution.checkout("A,B,C");
        Assert.assertEquals(Integer.valueOf(100), actual);
    }

    @Test
    public void checkout_shouldCalculateMultibuyCorrectly() {
        Integer actual = this.checkoutSolution.checkout("A,A,A");
        Assert.assertEquals(Integer.valueOf(130), actual);
    }
}

