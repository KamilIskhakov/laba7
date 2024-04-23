package Controler.Commands;

import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class RemoveHeadCommand implements Command {

    @Override
    public String getDescription() {
        return  getName() + "удаляет первый элемент очереди";
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.removeHead();
        return new ServerResponse(ExecuteCode.SUCCESS);
    }

}
