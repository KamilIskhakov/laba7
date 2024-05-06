package Server.ConcreteCommands;

import Controler.RequestFactory.FilterLessThanLocationRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class FilterLessThanLocationCommand implements Command {
    private FilterLessThanLocationRequest request;

    public FilterLessThanLocationCommand(Request request){
        this.request = (FilterLessThanLocationRequest) request;
    }

    @Override
    public String getDescription() {
        return getName()+"выводит объекты коллекции, у которых поле location меньше заданного";
    }

    @Override
    public String getName() {
        return "filterr_less_than_location";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(),Server.collectionManager.FilterLessThanLocation(request.getLocation()));
    }

}
