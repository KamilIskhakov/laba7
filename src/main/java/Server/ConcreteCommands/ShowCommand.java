package Server.ConcreteCommands;

import Controler.RequestFactoryDTO.RequestDTO;
import Controler.RequestFactoryDTO.ShowRequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

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
        return new ServerResponse(getName(), Server.collectionManager.showCollection());
    }
}
