package Groupld.Client.RequestFactory;

import Groupld.Controler.Exceptions.NotCorrectException;
import Groupld.Controler.RequestFactoryDTO.ExecuteScriptRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

public class ExecuteScriptRequest implements Request {
    private String filepath;
    @Override
    public RequestDTO reque(String args) throws NotCorrectException {
        if (args!=null){
            filepath = args;
            return new ExecuteScriptRequestDTO(filepath);
        }else{
            throw new NotCorrectException();
        }
    }

    public String getFilepath() {
        return filepath;
    }

}
