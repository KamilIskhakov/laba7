package Server.ConcreteCommands;

import Controler.RequestFactory.ClearRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class ClearCommand implements Command {
    private ClearRequest request;

    public ClearCommand(Request request){
        this.request = (ClearRequest) request;
    }

    @Override
    public String getDescription() {
        return getName() + "очищает коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.clear();
        return new ServerResponse(getName());
    }

}
