package Controler.ConcreteCommands;

import Controler.Command;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class ClearCommand implements Command {

    @Override
    public String getDescription() {
        return getName() + "очищает коллекцию";
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.clear();
        return new ServerResponse(getName());
    }

}
