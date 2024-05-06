package Server.ConcreteCommands;

import Controler.RequestFactory.InfoRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class InfoCommand implements Command {
    private InfoRequest request;

    public InfoCommand(Request request){
        this.request = (InfoRequest) request;
    }
    @Override
    public String getDescription() {
        return getName() + "вывести информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(), Server.collectionManager.collectionInfo());
    }

}