package by.prokhorenko.threads.exception;

public class ResourceGettingException extends Exception {
    public ResourceGettingException() {
    }

    public ResourceGettingException(String message) {
        super(message);
    }

    public ResourceGettingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceGettingException(Throwable cause) {
        super(cause);
    }
}
