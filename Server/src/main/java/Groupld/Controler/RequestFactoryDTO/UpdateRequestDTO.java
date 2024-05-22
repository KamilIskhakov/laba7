package Groupld.Controler.RequestFactoryDTO;

import Groupld.Controler.CollectionObjects.Person;

public class UpdateRequestDTO implements RequestDTO {
    private String token;
    private Person person;
    private Integer id;
    private String name;
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
