package Groupld.Controler.RequestFactoryDTO;

public class RemoveByIdRequestDTO implements RequestDTO {
    private String token;
    private Integer id;
    private String name;
    public RemoveByIdRequestDTO(Integer id){
        this.id = id;
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

}
