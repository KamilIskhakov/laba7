package Controler.Commands;

import Controler.Command;
import Server.ServerEntryPoint;

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
    public void execute() {
        ServerEntryPoint.collectionManager.removeHead();
    }
}
