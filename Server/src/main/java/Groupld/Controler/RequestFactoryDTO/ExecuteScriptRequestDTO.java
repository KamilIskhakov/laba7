package Groupld.Controler.RequestFactoryDTO;

public class ExecuteScriptRequestDTO implements RequestDTO {
    private String filepath;
    private String token;
    private String name;
    public ExecuteScriptRequestDTO(String filepath){
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
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
