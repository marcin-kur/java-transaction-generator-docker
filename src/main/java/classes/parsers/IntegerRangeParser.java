package classes.parsers;

import classes.generators.Range;

public class IntegerRangeParser {
    public Range<Integer> parse(String valueToParse) {
        try {
            String[] parts = valueToParse.split(":");
            return new Range<>(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }
}