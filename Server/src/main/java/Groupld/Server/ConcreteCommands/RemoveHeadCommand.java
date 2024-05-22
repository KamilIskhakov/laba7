package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.RemoveHeadRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

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
        /*Server.collectionManager.removeHead();*/
        return new ServerResponse(getName(), Server.sqlCollectionManager.toString(), request.getToken());
    }

}
