package Controler.ConcreteCommands;

import Controler.Command;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class InfoCommand implements Command {
    @Override
    public String getDescription() {
        return getName() + "вывести информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(), Server.collectionManager.collectionInfo());
    }

}