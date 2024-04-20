package Controler.Commands;

import Client.Main;
import Controler.Command;
import Server.ServerEntryPoint;

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
    public void execute() {
        Main.terminalOutputManager.println(ServerEntryPoint.collectionManager.collectionInfo());
    }
}