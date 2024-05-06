package Server.ConcreteCommands;

import Controler.RequestFactory.Request;
import Controler.RequestFactory.UpdateRequest;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class UpdateCommand implements Command {
    private UpdateRequest request;

    public UpdateCommand(Request request){
        this.request = (UpdateRequest) request;
    }
    @Override
    public String getDescription() {
        return getName() + "заменяет на новый объект по айди";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.update(request.getPerson(),request.getId());
        return new ServerResponse(getName());
    }
}
