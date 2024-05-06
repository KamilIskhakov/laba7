package Server.ConcreteCommands;

import Controler.RequestFactoryDTO.HeadRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

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
        return new ServerResponse(getName(),Server.collectionManager.showHead());
    }

}
