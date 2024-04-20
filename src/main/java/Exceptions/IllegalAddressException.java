package Exceptions;

public class IllegalAddressException extends Exception {
    private final String message;

    public IllegalAddressException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}