package Controler.Handlers;

import Controler.Command;
import Exceptions.NotCorrectException;

import java.io.Serializable;

public interface Handler extends Serializable {
    public void handle(String args) throws NotCorrectException;
    public Command getCommand();
    public void CreateCommand();
}
