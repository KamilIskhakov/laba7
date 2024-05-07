package Groupld.Server.ConcreteCommands;

import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class SaveCommand implements Command {

    @Override
    public String getDescription() {
        return getName() + "сохраняет все изменения в коллекции";
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.save();
        return new ServerResponse(getName());
    }
}
