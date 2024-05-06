package Server.ConcreteCommands;

import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Command;
import Server.Server;

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
