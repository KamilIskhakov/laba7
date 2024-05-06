package Server.ConcreteCommands;

import Controler.RequestFactoryDTO.FilterGreaterThanHeightRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

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
        return new ServerResponse(Server.collectionManager.FilterGreaterThanHeight(request.getHeight()));
    }

}
