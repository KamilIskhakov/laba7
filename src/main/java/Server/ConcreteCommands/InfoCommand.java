package Server.ConcreteCommands;

import Controler.RequestFactoryDTO.InfoRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

public class InfoCommand implements Command {
    private InfoRequestDTO request;

    public InfoCommand(RequestDTO request){
        this.request = (InfoRequestDTO) request;
    }
    @Override
    public String getDescription() {
        return getName() + "вывести информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(), Server.collectionManager.collectionInfo());
    }

}