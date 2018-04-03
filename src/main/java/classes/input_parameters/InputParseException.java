package classes.input_parameters;

public class InputParseException extends RuntimeException {
    public InputParseException(String message, Exception e) {
        super(message, e);
    }

    public InputParseException(String message) {
        super(message);
    }
}
