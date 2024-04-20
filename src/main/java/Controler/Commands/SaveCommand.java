package Controler.Commands;

import Controler.Command;
import Server.ServerEntryPoint;

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
    public void execute() {
        ServerEntryPoint.collectionManager.save();
    }
}
