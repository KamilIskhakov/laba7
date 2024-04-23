package Controler.Commands;

import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class ShowCommand implements Command {
    @Override
    public String getDescription() {
        return getName() + "выводит элементы коллекции";
    }

    @Override
    public String getName() {
        return "show ";
    }

    @Override
    public ServerResponse execute(){
        return new ServerResponse(Server.collectionManager.showCollection(),ExecuteCode.VALUE);
    }
}
