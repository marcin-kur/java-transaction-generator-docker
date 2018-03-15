package classes;

public class InputIntegerRangeParser {
    public IntegerRange parse(String valueToParse) {
        try {
            String[] parts = valueToParse.split(":");
            return new IntegerRange(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        } catch (Exception e) {
            throw new InputParseException(e.getMessage());
        }
    }
}