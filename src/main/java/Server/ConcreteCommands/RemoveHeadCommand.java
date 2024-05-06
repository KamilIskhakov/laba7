package Server.ConcreteCommands;

import Client.RequestFactoryDTO.RemoveHeadRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

public class RemoveHeadCommand implements Command {
    private RemoveHeadRequestDTO request;

    public RemoveHeadCommand(RequestDTO request){
        this.request = (RemoveHeadRequestDTO) request;
    }

    @Override
    public String getDescription() {
        return  getName() + "удаляет первый элемент очереди";
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.removeHead();
        return new ServerResponse(getName());
    }

}
