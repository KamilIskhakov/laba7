package Controler.RequestFactory;

import Controler.Exceptions.NotCorrectException;

public class ExecuteScriptRequest implements Request {
    private String filepath;
    @Override
    public void reque(String args) throws NotCorrectException {
        if (args!=null){
            filepath = args;
        }else{
            throw new NotCorrectException();
        }
    }

    public String getFilepath() {
        return filepath;
    }

}
