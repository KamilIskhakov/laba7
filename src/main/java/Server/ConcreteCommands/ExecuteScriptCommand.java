package Server.ConcreteCommands;

import Client.RequestFactoryDTO.ExecuteScriptRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;

public class ExecuteScriptCommand implements Command {
    private ExecuteScriptRequestDTO request;

    public ExecuteScriptCommand(RequestDTO request){
        this.request = (ExecuteScriptRequestDTO) request;
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
