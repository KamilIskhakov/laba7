package Groupld.Controler.Exceptions;

public class NoConnectionException extends Exception {
    public String getMessage() {
        return "It seems like server is not available now. Try latter";
    }
}
