package Server.ConcreteCommands;

import Controler.RequestFactory.ExitRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;

public class ExitCommand implements Command {
    private ExitRequest request;

    public ExitCommand(Request request){
        this.request = (ExitRequest) request;
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
