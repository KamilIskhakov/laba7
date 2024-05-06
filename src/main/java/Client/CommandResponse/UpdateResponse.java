package Client.CommandResponse;

import Client.Client;
import Controler.ChannelClientServerUtil.ServerResponse;

public class UpdateResponse implements Response{
    @Override
    public void open(ServerResponse serverResponse) {
        Client.terminalOutput.printlnWellDoneCommandMessage();
    }
}
