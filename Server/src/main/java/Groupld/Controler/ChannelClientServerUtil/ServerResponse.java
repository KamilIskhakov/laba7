package Groupld.Controler.ChannelClientServerUtil;

import Groupld.Controler.Response;

import java.io.Serializable;

public class ServerResponse implements Serializable, Response {
    private final String message;
    private final String commandName;
    private final String token;

    public ServerResponse(String commandName, String message, String token) {
        this.commandName = commandName;
        this.message = message;
        this.token = token;
    }

    public ServerResponse(String commandName, String token) {
        this.commandName = commandName;
        this.message = null;
        this.token = token;
    }
    public String getCommandName(){
        return commandName;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "ServerResponse{"
                + " message='" + message + '\''
                + '}';
    }
}