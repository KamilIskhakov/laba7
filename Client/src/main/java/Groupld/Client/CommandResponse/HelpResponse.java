package Groupld.Client.CommandResponse;

import Groupld.Client.Client;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;

public class HelpResponse implements Response {
    @Override
    public void open(ServerResponse serverResponse) {

        Client.terminalOutput.printlnWellDoneCommandMessage();
        Client.terminalOutput.println(serverResponse.getMessage());
    }
}
