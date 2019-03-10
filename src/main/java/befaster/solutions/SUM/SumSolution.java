package befaster.solutions.SUM;

import befaster.runner.SolutionNotImplementedException;

public class SumSolution {

    public int compute(int x, int y) {
        if(isValidValue(x) && isValidValue(y)) {
            return x + y;
        }
        throw new IllegalArgumentException("Invalid parameters");
    }

    private boolean isValidValue(int value) {
        return value >= 0 && value <= 100;
    }

}
