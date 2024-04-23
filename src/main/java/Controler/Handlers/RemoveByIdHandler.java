package Controler.Handlers;

import Controler.Command;
import Controler.Commands.RemoveByIdCommand;
import Exceptions.NotCorrectException;

public class RemoveByIdHandler implements Handler{
    private Integer id;
    private Command command;
    @Override
    public void handle(String args) throws NotCorrectException {
        if (args != ""){
            try{
                id = Integer.parseInt(args);
            }catch (Exception e){
                throw new NotCorrectException();
            }
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
        this.command = new RemoveByIdCommand(id);
    }
}
