package Server.ConcreteCommands;

import Controler.RequestFactory.Request;
import Controler.RequestFactory.ShowRequest;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class ShowCommand implements Command {
    private ShowRequest request;

    public ShowCommand(Request request){
        this.request = (ShowRequest) request;
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
