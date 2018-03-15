package classes;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class InputTimestampRangeParser {

    private final DateTimeFormatter formatter;

    public InputTimestampRangeParser() {
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    public TimestampRange parse(String valueToParse) {
        try {
            int midpoint = valueToParse.length() / 2;
            String lowerLimit = valueToParse.substring(0, midpoint);
            String upperLimit = valueToParse.substring(midpoint + 1);
            return new TimestampRange(getZonedDateTime(lowerLimit), getZonedDateTime(upperLimit));
        } catch(Exception e) {
            throw new InputParseException(e.getMessage());
        }
    }

    private ZonedDateTime getZonedDateTime(String value) {
        return ZonedDateTime.parse(value, formatter);
    }
}
