package Client.CommandResponse;

import Client.Client;
import Controler.RequestToServer.ServerResponse;

public class InfoResponse implements Response{
    @Override
    public void open(ServerResponse serverResponse) {
        Client.terminalOutput.printlnWellDoneCommandMessage();
        Client.terminalOutput.println(serverResponse.getMessage());
    }
}
