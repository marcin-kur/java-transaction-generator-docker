package classes.generators;

import java.util.Random;

public class IntegerGenerator {
    private final IntegerRange integerRange;
    private final Random random;

    public IntegerGenerator(IntegerRange integerRange) {
        this.integerRange = integerRange;
        this.random = new Random();
    }

    public int generate() {
        return integerRange.getLowerLimit() + random.nextInt(integerRange.getUpperLimit() - integerRange.getLowerLimit());
    }
}
