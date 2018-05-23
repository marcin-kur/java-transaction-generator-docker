package classes.parsers;

import classes.generators.Range;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampRangeParser {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public Range<ZonedDateTime> parse(String valueToParse) {
        try {
            int midpoint = valueToParse.length() / 2;
            String lowerLimit = valueToParse.substring(0, midpoint);
            String upperLimit = valueToParse.substring(midpoint + 1);
            return new Range<>(getZonedDateTime(lowerLimit), getZonedDateTime(upperLimit));
        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }

    private ZonedDateTime getZonedDateTime(String value) {
        return ZonedDateTime.parse(value, FORMATTER);
    }
}
