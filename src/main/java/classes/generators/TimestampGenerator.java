package classes.generators;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class TimestampGenerator {

    private final Range<ZonedDateTime> timestampRange;

    public TimestampGenerator(Range<ZonedDateTime> timestampRange) {
        this.timestampRange = timestampRange;
    }

    public ZonedDateTime generate() {
        long until = timestampRange.getLowerLimit().until(timestampRange.getUpperLimit(), ChronoUnit.MILLIS);
        long randomLong = (long) (Math.random() * (until));
        return timestampRange.getLowerLimit().plus(randomLong, ChronoUnit.MILLIS);
    }
}
