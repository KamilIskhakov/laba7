package Client.Exceptions;

public class NotCorrectException extends Exception {
    public String getMessage() {
        return "Invalid input. Work with the collection will be finished";
    }
}