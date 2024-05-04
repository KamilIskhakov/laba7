package Client.CommandFactory;

import Controler.Command;
import Controler.ConcreteCommands.HeadCommand;
import Controler.Exceptions.NotCorrectException;

public class HeadHandler implements Handler{
    private Command command;
    @Override
    public void handle(String args) throws NotCorrectException {
        if (args == ""){
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
        this.command = new HeadCommand();
    }
}
