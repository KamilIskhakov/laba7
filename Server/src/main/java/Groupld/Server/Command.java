package Groupld.Server;

import Groupld.Controler.ChannelClientServerUtil.ServerResponse;

import java.io.Serializable;

public interface Command extends Serializable {
    public String getDescription();

    public String getName();

    public ServerResponse execute();

}
