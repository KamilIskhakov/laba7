package Groupld.Client.RequestFactory;


import Groupld.Client.Client;
import Groupld.Controler.CollectionObjects.Location;
import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.FilterLessThanLocationRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

public class FilterLessThanLocationRequest implements Request {
    private Location location;
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args!=null){
            this.location = Client.terminalManager.MakeMeLocation();
            return new FilterLessThanLocationRequestDTO(location);
        }else{
            throw new NotCorrectException();
        }
    }

    public Location getLocation() {
        return location;
    }
}
