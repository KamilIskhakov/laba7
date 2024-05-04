package Client.CommandResponse;

import Client.Client;
import Controler.RequestToServer.ServerResponse;

public class RemoveHeadResponse implements Response{
    @Override
    public void open(ServerResponse serverResponse) {
        Client.terminalOutput.printlnWellDoneCommandMessage();
    }
}
