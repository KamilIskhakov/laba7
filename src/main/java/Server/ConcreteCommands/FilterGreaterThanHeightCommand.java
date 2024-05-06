package Server.ConcreteCommands;

import Controler.RequestFactory.FilterGreaterThanHeightRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class FilterGreaterThanHeightCommand implements Command {
    private FilterGreaterThanHeightRequest request;

    public FilterGreaterThanHeightCommand(Request request){
        this.request = (FilterGreaterThanHeightRequest) request;
    }
    @Override
    public String getDescription() {
        return getName() + "выводит объекты коллекции, у которых поле height меньше заданного";
    }

    @Override
    public String getName() {
        return "filter_greater_than_height";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(Server.collectionManager.FilterGreaterThanHeight(request.getHeight()));
    }

}
