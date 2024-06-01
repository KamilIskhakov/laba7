package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.ClearRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.collectionmanagers.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClearCommand implements Command {
    private ReceivedData receivedData;

    public ClearCommand(ReceivedData request){
        this.receivedData = request;
    }

    @Override
    public String getDescription() {
        return getName() + " очищает коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public ServerResponse execute() {
        User user = new User();
        user.setUsername(receivedData.getUsername());
        Server.personRepository.deleteAllOwned(user.getUsername());
        return new ServerResponse(getName(),receivedData.getRequest().getToken());
    }

}
