package Server.ConcreteCommands;

import Client.RequestFactoryDTO.AddRequestDTO;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

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
        Server.collectionManager.addToCollection(request.getPerson());
        return new ServerResponse(getName());
    }
}
