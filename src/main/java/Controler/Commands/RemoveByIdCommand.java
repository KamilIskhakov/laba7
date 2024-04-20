package Controler.Commands;

import Controler.Command;
import Server.ServerEntryPoint;

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
    public void execute() {
        ServerEntryPoint.collectionManager.removeCollectionById(argument);
    }
}
