package Controler.Commands;

import CollectionObjects.Person;
import Controler.Command;
import Service.ServerEntryPoint;

public class AddCommand implements Command {
    private Person person;

    public AddCommand(Person person){
        this.person = person;
    }

    @Override
    public String getDescription() {
        return getName() + "добавляет новый объект в конец коллекции";
    }

    @Override
    public String getName() {
        return "add ";
    }

    @Override
    public void execute(){
        ServerEntryPoint.collectionManager.addToCollection(person);
    }
}
