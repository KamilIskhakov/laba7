package Controler.RequestFactory;

import Controler.Exceptions.NotCorrectException;

import java.io.Serializable;

public interface Request extends Serializable {
    public void reque(String args) throws NotCorrectException;

    /*public String getUsername();

    public String getPassword();*/
}
