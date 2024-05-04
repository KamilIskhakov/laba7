package Client.CommandFactory;

import Controler.Command;
import Controler.Exceptions.NotCorrectException;

import java.io.Serializable;

public interface Handler extends Serializable {
    public void handle(String args) throws NotCorrectException;
    public Command getCommand();
    public void CreateCommand();
}
