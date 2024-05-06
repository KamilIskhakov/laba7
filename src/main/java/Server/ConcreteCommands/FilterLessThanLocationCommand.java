package Server.ConcreteCommands;

import Controler.RequestFactoryDTO.FilterLessThanLocationRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

public class FilterLessThanLocationCommand implements Command {
    private FilterLessThanLocationRequestDTO request;

    public FilterLessThanLocationCommand(RequestDTO request){
        this.request = (FilterLessThanLocationRequestDTO) request;
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
