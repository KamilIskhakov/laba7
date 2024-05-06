package Server.ConcreteCommands;

import Client.RequestFactoryDTO.RemoveByIdRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

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
