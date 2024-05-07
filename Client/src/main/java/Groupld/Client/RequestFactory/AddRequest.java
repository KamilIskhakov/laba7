package Groupld.Client.RequestFactory;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.AddRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Client.Client;

public class AddRequest implements Request {
    private Person person;
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args==null){
            this.person = Client.terminalManager.MakeMePerson();
            return new AddRequestDTO(person);
        }else{
            throw new NotCorrectException();
        }
    }

    public Person getPerson() {
        return person;
    }

}
