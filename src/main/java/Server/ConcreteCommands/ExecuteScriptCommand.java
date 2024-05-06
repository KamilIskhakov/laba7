package Server.ConcreteCommands;

import Controler.RequestFactory.ExecuteScriptRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;

public class ExecuteScriptCommand implements Command {
    private ExecuteScriptRequest request;

    public ExecuteScriptCommand(Request request){
        this.request = (ExecuteScriptRequest) request;
    }
    @Override
    public String getDescription() {
        return getName() + "считывает скрипт пользователя";
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(),request.getFilepath());
    }


}
