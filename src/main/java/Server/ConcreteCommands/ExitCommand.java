package Server.ConcreteCommands;

import Controler.RequestFactoryDTO.ExitRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;

public class ExitCommand implements Command {
    private ExitRequestDTO request;

    public ExitCommand(RequestDTO request){
        this.request = (ExitRequestDTO) request;
    }
    @Override
    public String getDescription() {
        return getName() + "прерывает программу с сохранением";
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public ServerResponse execute() {
        new SaveCommand().execute();
        return new ServerResponse(getName());
    }
}
