package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.HelpRequestDTO;
import Groupld.Controler.RequestFactoryDTO.InfoRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InfoCommand implements Command {
    private InfoRequestDTO request;
    private ReceivedData receivedData;

    public InfoCommand(ReceivedData request){

        this.request = (InfoRequestDTO) request.getRequest();
        this.receivedData = request;

    }

    @Override
    public String getDescription() {
        return getName() + " вывести информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(), Server.personRepository.toString(), request.getToken());
    }

}