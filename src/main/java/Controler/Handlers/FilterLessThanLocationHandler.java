package Controler.Handlers;

import Client.Client;
import CollectionObjects.Location;
import Controler.Command;
import Controler.Commands.FilterLessThanLocationCommand;
import Exceptions.NotCorrectException;

public class FilterLessThanLocationHandler implements Handler{
    private Location location;
    private Command command;

    @Override
    public void CreateCommand(){
        this.command = new FilterLessThanLocationCommand(location);
    }
    @Override
    public void handle(String args) throws NotCorrectException {
        if (args == ""){
            this.location = Client.terminalManager.MakeMeLocation();
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
