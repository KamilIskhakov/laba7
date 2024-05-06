package Server.ConcreteCommands;

import Client.RequestFactoryDTO.ClearRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

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
        Server.collectionManager.clear();
        return new ServerResponse(getName());
    }

}
