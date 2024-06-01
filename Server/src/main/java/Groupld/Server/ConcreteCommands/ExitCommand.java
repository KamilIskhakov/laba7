package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.ExecuteScriptRequestDTO;
import Groupld.Controler.RequestFactoryDTO.ExitRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;
import Groupld.Server.collectionmanagers.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ExitCommand implements Command {

    private ReceivedData receivedData;


    public ExitCommand(ReceivedData request){
        this.receivedData = request;

    }
    @Override
    public String getDescription() {
        return getName() + " прерывает программу с сохранением";
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public ServerResponse execute() {
        /*new SaveCommand().execute();*/
        return new ServerResponse(getName(),receivedData.getRequest().getToken());
    }
}
