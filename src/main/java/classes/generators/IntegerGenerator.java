package classes.generators;

import java.util.Random;

public class IntegerGenerator {
    private final Range<Integer> integerRange;
    private final Random random;

    public IntegerGenerator(Range<Integer> integerRange) {
        this.integerRange = integerRange;
        this.random = new Random();
    }

    public int generate() {
        return integerRange.getLowerLimit() + random.nextInt(1 + integerRange.getUpperLimit() - integerRange.getLowerLimit());
    }
}