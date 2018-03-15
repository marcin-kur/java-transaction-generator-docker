package classes;

public class InputIntegerParser {
    public int parse(String valueToParse) {
        try {
            return Integer.valueOf(valueToParse);
        } catch (Exception e) {
            throw new InputParseException(e.getMessage());
        }
    }
}