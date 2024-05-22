package Groupld.Controler.RequestFactoryDTO;

public class ShowRequestDTO implements RequestDTO {
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
    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public void setUserName(String userName) {
        this.name = userName;
    }

}
