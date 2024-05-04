package Controler.ConcreteCommands;

import Controler.Command;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class HeadCommand implements Command {
    @Override
    public String getDescription() {
        return getName() + "выводит первый элемент очереди";
    }

    @Override
    public String getName() {
        return "head";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(),Server.collectionManager.showHead());
    }

}
