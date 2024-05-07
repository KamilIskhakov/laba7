package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.FilterLessThanLocationRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

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
