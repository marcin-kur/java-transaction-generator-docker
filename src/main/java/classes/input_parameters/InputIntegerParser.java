package classes.input_parameters;

public class InputIntegerParser {
    public int parse(String valueToParse) {
        try {
            return Integer.valueOf(valueToParse);
        } catch (NumberFormatException e) {
            throw new InputParseException(e.getMessage());
        }
    }
}