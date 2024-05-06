package Client.CommandResponse;

import Client.Client;
import Controler.ChannelClientServerUtil.ServerResponse;

public class HelpResponse implements Response{
    @Override
    public void open(ServerResponse serverResponse) {
        Client.terminalOutput.printlnWellDoneCommandMessage();
    }
}
