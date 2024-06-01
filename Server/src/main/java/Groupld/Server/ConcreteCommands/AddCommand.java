package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.AddRequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.collectionmanagers.User;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
public class AddCommand implements Command {
    private ReceivedData request;
    private AddRequestDTO person;

    public AddCommand(ReceivedData request){

        this.request = request;
        person = (AddRequestDTO) request.getRequest();
    }

    @Override
    public String getDescription() {
        return getName() + " добавляет новый объект в конец коллекции";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public ServerResponse execute(){
        User user = new User();
        user.setUsername(request.getUsername());
        Server.personRepository.savePerson(person.getPerson(), user);
        return new ServerResponse(getName(),request.getRequest().getToken());/*new ServerResponse(getName(), request.getToken());*/
    }
}
