package befaster.solutions.SUM;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SumSolutionTest {
    private SumSolution sum;

    @Before
    public void setUp() {
        sum = new SumSolution();
    }

    @Test
    public void compute_correctlyAddsTwoValidValues() {
        assertThat(sum.compute(1, 1), equalTo(2));
        assertThat(sum.compute(50, 100), equalTo(150));
        assertThat(sum.compute(0,32), equalTo(32));
    }

    @Test(expected = IllegalArgumentException.class)
    public void compute_throwsIllegalArgumentExceptionIfNegative() {
        sum.compute(-1, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void compute_throwsIllegalArgumentExceptionIfOver100() {
        sum.compute(1, 101);
    }
}


