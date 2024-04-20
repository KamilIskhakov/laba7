package Controler.Commands;

import CollectionObjects.Person;
import Controler.Command;
import Server.ServerEntryPoint;

public class UpdateCommand implements Command {
    private Integer argument;
    private Person person;
    public UpdateCommand(Integer argument, Person person){
        this.argument = argument;
        this.person =person;
    }
    @Override
    public String getDescription() {
        return getName() + "заменяет на новый объект по айди";
    }

    @Override
    public String getName() {
        return "update ";
    }

    @Override
    public void execute() {
        ServerEntryPoint.collectionManager.update(person,argument);
    }
}
