package classes.generators;

public class IntegerRange {
    private final int lowerLimit;
    private final int upperLimit;

    public IntegerRange(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public int getLowerLimit() {
        return lowerLimit;
    }

    public int getUpperLimit() {
        return upperLimit;
    }
}
