package Groupld.Controler.RequestFactoryDTO;

public class HelpRequestDTO implements RequestDTO {
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
