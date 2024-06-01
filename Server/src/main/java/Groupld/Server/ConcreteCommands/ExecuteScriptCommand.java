package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.ExecuteScriptRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Util.ReceivedData;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExecuteScriptCommand implements Command {
    private ReceivedData receivedData;
    private ExecuteScriptRequestDTO executeScriptRequestDTO;

    public ExecuteScriptCommand(ReceivedData request){
        this.receivedData = request;
        executeScriptRequestDTO = (ExecuteScriptRequestDTO) receivedData.getRequest();
    }
    @Override
    public String getDescription() {
        return getName() + " считывает скрипт пользователя";
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(),executeScriptRequestDTO.getFilepath(),receivedData.getRequest().getToken());
    }


}
