package Groupld.Controler;

import java.io.Serializable;

public class PullingResponse implements Serializable,Response {
    private final String token;
    private final String message;

    public PullingResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }
    public String getMessage(){
        return message;
    }

}
