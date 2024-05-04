package Controler.ConcreteCommands;

import Controler.Command;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class ShowCommand implements Command {
    @Override
    public String getDescription() {
        return getName() + "выводит элементы коллекции";
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public ServerResponse execute(){
        return new ServerResponse(getName(), Server.collectionManager.showCollection());
    }
}
