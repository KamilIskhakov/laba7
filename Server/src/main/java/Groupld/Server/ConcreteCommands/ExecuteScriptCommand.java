package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.ExecuteScriptRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;

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
        return new ServerResponse(getName(),request.getFilepath(), request.getToken());
    }


}
