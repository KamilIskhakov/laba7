package Groupld.Controler.RequestFactoryDTO;


public class ClearRequestDTO implements RequestDTO {
    private String token;
    private String name;
    @Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

}
