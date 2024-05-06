package Client.CommandResponse;

import Client.Client;
import Controler.ChannelClientServerUtil.ServerResponse;

public class ExitResponse implements Response{
    @Override
    public void open(ServerResponse serverResponse) {
        Client.terminalOutput.printlnWellDoneCommandMessage();
        System.exit(0);
    }
}
