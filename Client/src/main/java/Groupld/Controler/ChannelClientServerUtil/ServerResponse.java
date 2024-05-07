package Groupld.Controler.ChannelClientServerUtil;

import java.io.Serializable;

public class ServerResponse implements Serializable {
    private final String message;
    private final String commandName;

    public ServerResponse(String commandName, String message) {
        this.commandName = commandName;
        this.message = message;
    }

    public ServerResponse(String commandName) {
        this.commandName = commandName;
        this.message = null;
    }
    public String getCommandName(){
        return commandName;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public String toString() {
        return "ServerResponse{"
                + " message='" + message + '\''
                + '}';
    }
}