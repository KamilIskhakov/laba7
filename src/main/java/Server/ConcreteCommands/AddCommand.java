package Server.ConcreteCommands;

import Controler.RequestFactory.AddRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class AddCommand implements Command {
    private AddRequest request;

    public AddCommand(Request request){
        this.request = (AddRequest) request;
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
