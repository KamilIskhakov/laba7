package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.RemoveByIdRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class RemoveByIdCommand implements Command {
    private RemoveByIdRequestDTO request;

    public RemoveByIdCommand(RequestDTO request){
        this.request = (RemoveByIdRequestDTO) request;
    }
    @Override
    public String getDescription() {
        return getName() + "удаляет элемент очереди по айди";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.removeCollectionById(request.getId());
        return new ServerResponse(getName());
    }
}
