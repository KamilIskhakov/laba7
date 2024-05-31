package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.ClearRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class ClearCommand implements Command {
    private ClearRequestDTO request;

    public ClearCommand(RequestDTO request){
        this.request = (ClearRequestDTO) request;
    }

    @Override
    public String getDescription() {
        return getName() + "очищает коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public ServerResponse execute() {
        /*Server.sqlCollectionManager.clear(request);*/
        return null;/*new ServerResponse(getName(), request.getToken());*/
    }

}
