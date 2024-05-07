package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

public interface Request{
    public RequestDTO reque(String args) throws NotCorrectException;

    /*public String getUsername();

    public String getPassword();*/
}
