package Client.CommandFactory;

import Controler.Command;
import Controler.ConcreteCommands.ExecuteScriptCommand;
import Controler.Exceptions.NotCorrectException;

public class ExecuteScriptHandler implements Handler{
    private Command command;
    private String filepath;
    @Override
    public void handle(String args) throws NotCorrectException {
        if (args != ""){
            filepath = args;
            CreateCommand();
        }else{
            throw new NotCorrectException();
        }
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public void CreateCommand() {
        this.command = new ExecuteScriptCommand(filepath);
    }
}
