package Groupld.Client.CommandResponse;

import Groupld.Client.Client;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;

public class ExitResponse implements Response{
    @Override
    public void open(ServerResponse serverResponse) {
        Client.terminalOutput.printlnWellDoneCommandMessage();
        System.exit(0);
    }
}
