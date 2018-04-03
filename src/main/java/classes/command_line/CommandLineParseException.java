package classes.command_line;

public class CommandLineParseException extends RuntimeException {
    public CommandLineParseException(String message, Exception e) {
        super(message, e);
    }
}
