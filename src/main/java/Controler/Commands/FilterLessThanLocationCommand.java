package Controler.Commands;

import CollectionObjects.Location;
import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class FilterLessThanLocationCommand implements Command {
    private Location location;
    public FilterLessThanLocationCommand(Location location){
        this.location = location;
    }

    @Override
    public String getDescription() {
        return getName()+"выводит объекты коллекции, у которых поле location меньше заданного";
    }

    @Override
    public String getName() {
        return "filterr_less_than_location ";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(Server.collectionManager.FilterLessThanLocation(location),ExecuteCode.VALUE);
    }

}
