package Controler.ConcreteCommands;

import Controler.CollectionObjects.Person;
import Controler.Command;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

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
        return "update";
    }

    @Override
    public ServerResponse execute() {
        Server.collectionManager.update(person,argument);
        return new ServerResponse(getName());
    }
}
