package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.HeadRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class HeadCommand implements Command {
    private HeadRequestDTO request;

    public HeadCommand(RequestDTO request){
        this.request = (HeadRequestDTO) request;
    }
    @Override
    public String getDescription() {
        return getName() + "выводит первый элемент очереди";
    }

    @Override
    public String getName() {
        return "head";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(), Server.sqlCollectionManager.toString(), request.getToken());
    }

}
