package Controler.Commands;

import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class ExitCommand implements Command {
    @Override
    public String getDescription() {
        return getName() + "прерывает программу без сохранения";
    }

    @Override
    public String getName() {
        return "exit ";
    }

    @Override
    public ServerResponse execute() {
        new SaveCommand().execute();
        return new ServerResponse(ExecuteCode.EXIT);
    }
}
