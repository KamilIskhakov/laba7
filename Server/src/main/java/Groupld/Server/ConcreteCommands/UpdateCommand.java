package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.RequestFactoryDTO.UpdateRequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class UpdateCommand implements Command {
    private UpdateRequestDTO request;

    public UpdateCommand(RequestDTO request){
        this.request = (UpdateRequestDTO) request;
    }
    @Override
    public String getDescription() {
        return getName() + "заменяет на новый объект по айди";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.update(request.getPerson(),request.getId());
        return new ServerResponse(getName());
    }
}
