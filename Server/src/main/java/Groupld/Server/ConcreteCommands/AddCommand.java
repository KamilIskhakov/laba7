package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.AddRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class AddCommand implements Command {
    private AddRequestDTO request;

    public AddCommand(RequestDTO request){
        this.request = (AddRequestDTO) request;
    }

    @Override
    public String getDescription() {
        return getName() + "добавляет новый объект в конец коллекции";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public ServerResponse execute(){
        request.getPerson().setOwnerUsername(request.getUserName());
        Server.sqlCollectionManager.addToCollection(request.getPerson());
        return new ServerResponse(getName(), request.getToken());
    }
}
