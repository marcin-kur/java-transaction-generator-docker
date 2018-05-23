package classes.parsers;

public class ParseException extends RuntimeException {
    public ParseException(String message, Exception e) {
        super(message, e);
    }

    public ParseException(String message) {
        super(message);
    }
}
