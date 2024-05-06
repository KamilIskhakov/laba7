package Controler.RequestFactory;

import Client.Client;
import Controler.CollectionObjects.Person;
import Controler.Exceptions.NotCorrectException;

public class AddRequest implements Request {
    private Person person;
    @Override
    public void reque(String args) throws NotCorrectException {
        if (args==null){
            this.person = Client.terminalManager.MakeMePerson();

        }else{
            throw new NotCorrectException();
        }
    }

    public Person getPerson() {
        return person;
    }

}
