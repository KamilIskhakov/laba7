package Controler.Commands;

import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class SaveCommand implements Command {

    @Override
    public String getDescription() {
        return getName() + "сохраняет все изменения в коллекции";
    }

    @Override
    public String getName() {
        return "save ";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.save();
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}
