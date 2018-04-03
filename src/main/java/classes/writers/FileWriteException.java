package classes.writers;

public class FileWriteException extends RuntimeException {
    public FileWriteException(String message, Exception e) {
        super(message, e);
    }
}
