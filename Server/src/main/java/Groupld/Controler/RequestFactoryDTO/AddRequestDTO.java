package Groupld.Controler.RequestFactoryDTO;
import Groupld.Controler.CollectionObjects.Person;

public class AddRequestDTO implements RequestDTO {
    private String token;
    private Person person;
    private String name;
    public AddRequestDTO(Person person){
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public void setUserName(String userName) {
        this.name = userName;
    }
}
