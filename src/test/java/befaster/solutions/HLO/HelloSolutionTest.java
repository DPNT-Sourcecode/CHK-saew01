package befaster.solutions.HLO;

import org.junit.Assert;
import org.junit.Test;

public class HelloSolutionTest {

    private HelloSolution helloSolution = new HelloSolution();

    @Test
    public void hello_returnsExpected() {
        String actual = helloSolution.hello("something");
        Assert.assertEquals("Hello World", actual);
    }
}

