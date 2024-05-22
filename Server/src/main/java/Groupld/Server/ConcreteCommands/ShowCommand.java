package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.RequestFactoryDTO.ShowRequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class ShowCommand implements Command {
    private ShowRequestDTO request;

    public ShowCommand(RequestDTO request){
        this.request = (ShowRequestDTO) request;
    }
    @Override
    public String getDescription() {
        return getName() + "выводит элементы коллекции";
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public ServerResponse execute(){
        return new ServerResponse(getName(), Server.sqlCollectionManager.toString(), request.getToken());
    }
}
