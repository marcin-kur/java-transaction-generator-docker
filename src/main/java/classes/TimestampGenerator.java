package classes;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class TimestampGenerator {

    private final TimestampRange timestampRange;

    public TimestampGenerator(TimestampRange timestampRange) {
        this.timestampRange = timestampRange;
    }

    public ZonedDateTime generate() {
        long until = timestampRange.getLowerLimit().until(timestampRange.getUpperLimit(), ChronoUnit.MILLIS);
        long randomLong = (long) (Math.random() * (until));
        return timestampRange.getLowerLimit().plus(randomLong, ChronoUnit.MILLIS);
    }
}
