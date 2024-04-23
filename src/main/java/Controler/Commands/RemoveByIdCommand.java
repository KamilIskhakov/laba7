package Controler.Commands;

import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class RemoveByIdCommand implements Command {
    private Integer argument;
    public RemoveByIdCommand(Integer argument){
        this.argument = argument;
    }
    @Override
    public String getDescription() {
        return getName() + "удаляет элемент очереди по айди";
    }

    @Override
    public String getName() {
        return "remove_by_id ";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.removeCollectionById(argument);
        return new ServerResponse(ExecuteCode.SUCCESS);
    }
}
