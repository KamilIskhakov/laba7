package Controler.Handlers;

import Client.Main;
import CollectionObjects.Person;
import Controler.Commands.AddCommand;
import Controler.Command;
import Exceptions.NotCorrectException;
import lombok.Setter;

import java.io.Serializable;

public class AddHandler implements Handler {
    private Person person;
    private Command command;

    @Override
    public void CreateCommand(){
        this.command = new AddCommand(person);
    }
    @Override
    public void handle(String args) throws NotCorrectException {
        if (args == ""){
            this.person = Main.terminalManager.MakeMePerson();
            CreateCommand();
        }else{
            throw new NotCorrectException();
        }
    }
    @Override
    public Command getCommand() {
        return command;
    }

}
