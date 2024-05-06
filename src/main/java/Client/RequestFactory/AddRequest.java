package Client.RequestFactory;

import Client.Client;
import Controler.RequestFactoryDTO.AddRequestDTO;
import Controler.RequestFactoryDTO.RequestDTO;
import Controler.CollectionObjects.Person;
import Controler.Exceptions.NotCorrectException;

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
