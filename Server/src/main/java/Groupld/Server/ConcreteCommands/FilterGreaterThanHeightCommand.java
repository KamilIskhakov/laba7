package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.FilterGreaterThanHeightRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class FilterGreaterThanHeightCommand implements Command {
    private FilterGreaterThanHeightRequestDTO request;

    public FilterGreaterThanHeightCommand(RequestDTO request){
        this.request = (FilterGreaterThanHeightRequestDTO) request;
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
        return new ServerResponse(getName(), Server.sqlCollectionManager.toString(), request.getToken());
    }

}
