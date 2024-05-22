package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.InfoRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

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
        return new ServerResponse(getName(), Server.sqlCollectionManager.toString(), request.getToken());
    }

}