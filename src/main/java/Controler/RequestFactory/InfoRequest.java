package Controler.RequestFactory;

import Controler.Exceptions.NotCorrectException;

public class InfoRequest implements Request {
    @Override
    public void reque(String args) throws NotCorrectException {
        if (args==null){
        }else{
            throw new NotCorrectException();
        }
    }

}
