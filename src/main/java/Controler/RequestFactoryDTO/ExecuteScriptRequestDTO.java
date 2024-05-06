package Controler.RequestFactoryDTO;

public class ExecuteScriptRequestDTO implements RequestDTO {
    private String filepath;
    public ExecuteScriptRequestDTO(String filepath){
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

}
