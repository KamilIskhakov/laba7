package Server.ConcreteCommands;

import Controler.RequestFactory.RemoveByIdRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class RemoveByIdCommand implements Command {
    private RemoveByIdRequest request;

    public RemoveByIdCommand(Request request){
        this.request = (RemoveByIdRequest) request;
    }
    @Override
    public String getDescription() {
        return getName() + "удаляет элемент очереди по айди";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.removeCollectionById(request.getId());
        return new ServerResponse(getName());
    }
}
