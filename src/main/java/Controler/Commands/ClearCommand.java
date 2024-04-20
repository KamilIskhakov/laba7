package Controler.Commands;

import Controler.Command;
import Server.ServerEntryPoint;

public class ClearCommand implements Command {

    @Override
    public String getDescription() {
        return getName() + "очищает коллекцию";
    }

    @Override
    public String getName() {
        return "clear ";
    }

    @Override
    public void execute() {
        ServerEntryPoint.collectionManager.clear();
    }
}
