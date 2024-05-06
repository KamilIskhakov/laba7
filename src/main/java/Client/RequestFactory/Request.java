package Client.RequestFactory;

import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

public interface Request{
    public RequestDTO reque(String args) throws NotCorrectException;

    /*public String getUsername();

    public String getPassword();*/
}
