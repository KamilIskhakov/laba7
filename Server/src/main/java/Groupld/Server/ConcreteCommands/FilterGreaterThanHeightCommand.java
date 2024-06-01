package Groupld.Server.ConcreteCommands;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Controler.RequestFactoryDTO.ExecuteScriptRequestDTO;
import Groupld.Controler.RequestFactoryDTO.FilterGreaterThanHeightRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.collectionmanagers.User;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class FilterGreaterThanHeightCommand implements Command {
    private FilterGreaterThanHeightRequestDTO request;
    private ReceivedData receivedData;

    public FilterGreaterThanHeightCommand(ReceivedData request){
        this.receivedData = request;
        this.request = (FilterGreaterThanHeightRequestDTO) request.getRequest();
    }

    @Override
    public String getDescription() {
        return getName() + " выводит объекты коллекции, у которых поле height меньше заданного";
    }

    @Override
    public String getName() {
        return "filter_greater_than_height";
    }

    @Override
    public ServerResponse execute() {
        User user = new User();
        user.setUsername(receivedData.getUsername());
        List<Person> list = Server.personRepository.sortPersonsByHeight(user);
        return new ServerResponse(getName(), list.toString(), request.getToken());
    }

}
