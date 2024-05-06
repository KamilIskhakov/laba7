package Controler.RequestFactory;

import Controler.Exceptions.NotCorrectException;

public class GroupCountingByNameRequest implements Request {
    public void reque(String args) throws NotCorrectException {
        if (args==null){

        }else{
            throw new NotCorrectException();
        }
    }
}
