package Server.ConcreteCommands;

import Controler.RequestFactory.RemoveHeadRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class RemoveHeadCommand implements Command {
    private RemoveHeadRequest request;

    public RemoveHeadCommand(Request request){
        this.request = (RemoveHeadRequest) request;
    }

    @Override
    public String getDescription() {
        return  getName() + "удаляет первый элемент очереди";
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.removeHead();
        return new ServerResponse(getName());
    }

}
