package Groupld.Server.ConcreteCommands;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Controler.RequestFactoryDTO.RemoveHeadRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Controler.RequestFactoryDTO.ShowRequestDTO;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.Util.ServerHandlerRequestManager;
import Groupld.Server.collectionmanagers.User;
import Groupld.Server.collectionmanagers.UserPerson;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class RemoveHeadCommand implements Command {
    private RemoveHeadRequestDTO request;
    private ReceivedData receivedData;

    public RemoveHeadCommand(ReceivedData request){

        this.request = (RemoveHeadRequestDTO) request.getRequest();
        this.receivedData = request;

    }


    @Override
    public String getDescription() {
        return  getName() + " удаляет первый элемент очереди";
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    public ServerResponse execute() {
        /*Server.collectionManager.removeHead();*/
        User user = new User();
        user.setUsername(receivedData.getUsername());
        Server.personRepository.deletePerson(user);
        return new ServerResponse(getName(), request.getToken());
    }

}
