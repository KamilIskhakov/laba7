package Client.CommandFactory;

import Client.Client;
import Controler.CollectionObjects.Person;
import Controler.ConcreteCommands.AddCommand;
import Controler.Command;
import Controler.Exceptions.NotCorrectException;

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

}
