package Controler;

import Controler.RequestToServer.ServerResponse;

public interface Command {
    public String getDescription();

    public String getName();

    public ServerResponse execute();

}
