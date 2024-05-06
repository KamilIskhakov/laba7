package Controler.RequestFactory;

import Controler.Exceptions.NotCorrectException;

public class ShowRequest implements Request {

    @Override
    public void reque(String args) throws  NotCorrectException {
        if (args!=null){
            throw new NotCorrectException();
        }
    }

}
