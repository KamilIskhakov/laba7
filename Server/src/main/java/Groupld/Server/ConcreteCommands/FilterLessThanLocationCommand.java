package Groupld.Server.ConcreteCommands;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Controler.RequestFactoryDTO.FilterLessThanLocationRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.collectionmanagers.User;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class FilterLessThanLocationCommand implements Command {
    private FilterLessThanLocationRequestDTO request;
    private ReceivedData receivedData;

    public FilterLessThanLocationCommand(ReceivedData request){
        this.receivedData = request;
        this.request = (FilterLessThanLocationRequestDTO) request.getRequest();
    }

    @Override
    public String getDescription() {
        return getName()+" выводит объекты коллекции, у которых поле location меньше заданного";
    }

    @Override
    public String getName() {
        return "filterr_less_than_location";
    }

    @Override
    public ServerResponse execute() {
        User user = new User();
        user.setUsername(receivedData.getUsername());
        List<Person> list = Server.personRepository.sortPersonsByLocation(user);
        return new ServerResponse(getName(), list.toString(), request.getToken());
    }

}
