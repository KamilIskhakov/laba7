package Controler.Handlers;

import Client.Client;
import CollectionObjects.Person;
import Controler.Command;
import Controler.Commands.UpdateCommand;
import Exceptions.NotCorrectException;

public class UpdateHandler implements Handler{
    private Command command;
    private Person person;
    private Integer id;
    @Override
    public void handle(String args) throws NotCorrectException {
        if (args != ""){
            try{
                id = Integer.parseInt(args);
            }catch (Exception e){
                throw new NotCorrectException();
            }
            this.person = Client.terminalManager.MakeMePerson();
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
        this.command = new UpdateCommand(id,person);
    }
}
