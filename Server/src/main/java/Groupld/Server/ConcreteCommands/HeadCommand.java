package Groupld.Server.ConcreteCommands;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Controler.RequestFactoryDTO.ExecuteScriptRequestDTO;
import Groupld.Controler.RequestFactoryDTO.HeadRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.collectionmanagers.User;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class HeadCommand implements Command {
    private HeadRequestDTO request;
    private ReceivedData receivedData;

    public HeadCommand(ReceivedData request){
        this.receivedData = request;
        this.request = (HeadRequestDTO) request.getRequest();
    }
    @Override
    public String getDescription() {
        return getName() + " выводит первый элемент очереди";
    }

    @Override
    public String getName() {
        return "head";
    }

    @Override
    public ServerResponse execute() {
        User user = new User();
        user.setUsername(receivedData.getUsername());
        List<Person> list  = Server.personRepository.findPersonsByUser(user);
        return new ServerResponse(getName(), list.get(0).toString(), request.getToken());
    }

}
