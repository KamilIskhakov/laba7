package Controler.Commands;

import Client.Client;
import Controler.Command;
import Server.ServerEntryPoint;

public class HeadCommand implements Command {
    @Override
    public String getDescription() {
        return getName() + "выводит первый элемент очереди";
    }

    @Override
    public String getName() {
        return "head ";
    }

    @Override
    public void execute() {
      Client.terminalOutputManager.println(ServerEntryPoint.collectionManager.showHead());
    }
}
