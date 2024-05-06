package Client.RequestFactoryDTO;

import Controler.CollectionObjects.Person;

public class UpdateRequestDTO implements RequestDTO {
    private Person person;
    private Integer id;
    public UpdateRequestDTO(Person person, Integer id){
        this.person = person;
        this.id = id;
    }
    public Person getPerson() {
        return person;
    }
    public Integer getId() {
        return id;
    }

}
