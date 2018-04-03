package classes.generators;

import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class TimestampRange {
    private final ZonedDateTime lowerLimit;
    private final ZonedDateTime upperLimit;

    public TimestampRange(ZonedDateTime lowerLimit, ZonedDateTime upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }
}