package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.RequestFactoryDTO.ShowRequestDTO;
import Groupld.Controler.RequestFactoryDTO.UpdateRequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.collectionmanagers.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UpdateCommand implements Command {
    private UpdateRequestDTO request;
    private ReceivedData receivedData;

    public UpdateCommand(ReceivedData request){

        this.request = (UpdateRequestDTO) request.getRequest();
        this.receivedData = request;

    }
    @Override
    public String getDescription() {
        return getName() + " заменяет на новый объект по айди";
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public ServerResponse execute() {
        try {
            User user = new User();
            user.setUsername(receivedData.getUsername());
            request.getPerson().setId(request.getId());
            Server.personRepository.updatePerson(request.getPerson(), user);
        }catch (IllegalArgumentException e){
            return new ServerResponse(e.getMessage(),request.getToken());
        }
        return new ServerResponse(getName(), request.getToken());
    }
}
