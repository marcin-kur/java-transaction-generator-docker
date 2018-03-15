package classes;

import java.time.ZonedDateTime;

public class TimestampRange {
    private final ZonedDateTime lowerLimit;
    private final ZonedDateTime upperLimit;

    public TimestampRange(ZonedDateTime lowerLimit, ZonedDateTime upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public ZonedDateTime getLowerLimit() {
        return lowerLimit;
    }

    public ZonedDateTime getUpperLimit() {
        return upperLimit;
    }
}