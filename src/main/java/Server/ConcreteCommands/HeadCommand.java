package Server.ConcreteCommands;

import Controler.RequestFactory.HeadRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class HeadCommand implements Command {
    private HeadRequest request;

    public HeadCommand(Request request){
        this.request = (HeadRequest) request;
    }
    @Override
    public String getDescription() {
        return getName() + "выводит первый элемент очереди";
    }

    @Override
    public String getName() {
        return "head";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(),Server.collectionManager.showHead());
    }

}
