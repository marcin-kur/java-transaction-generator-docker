package classes.generators;

import lombok.Getter;

@Getter
public class IntegerRange {
    private final int lowerLimit;
    private final int upperLimit;

    public IntegerRange(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
}
