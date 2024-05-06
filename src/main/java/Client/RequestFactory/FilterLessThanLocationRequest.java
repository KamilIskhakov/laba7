package Client.RequestFactory;


import Client.Client;
import Controler.RequestFactoryDTO.FilterLessThanLocationRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.CollectionObjects.Location;
import Controler.Exceptions.NotCorrectException;

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
