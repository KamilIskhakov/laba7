package Controler.Commands;

import Client.Client;
import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class InfoCommand implements Command {
    @Override
    public String getDescription() {
        return getName() + "вывести информацию о коллекции";
    }

    @Override
    public String getName() {
        return "info ";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(Server.collectionManager.collectionInfo(),ExecuteCode.VALUE);
    }

}