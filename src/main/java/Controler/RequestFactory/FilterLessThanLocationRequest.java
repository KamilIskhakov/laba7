package Controler.RequestFactory;

import Client.Client;
import Controler.CollectionObjects.Location;
import Controler.Exceptions.NotCorrectException;

public class FilterLessThanLocationRequest implements Request {
    private Location location;
    @Override
    public void reque(String args) throws NotCorrectException {
        if (args!=null){
            this.location = Client.terminalManager.MakeMeLocation();
        }else{
            throw new NotCorrectException();
        }
    }

    public Location getLocation() {
        return location;
    }
}
