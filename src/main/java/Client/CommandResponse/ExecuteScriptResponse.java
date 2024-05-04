package Client.CommandResponse;

import Client.Client;
import Controler.RequestToServer.ServerResponse;

public class ExecuteScriptResponse implements Response{
    @Override
    public void open(ServerResponse serverResponse) {
        Client.terminalOutput.printlnWellDoneCommandMessage();
        Client.terminalInput.readScript(serverResponse.getMessage());
    }
}
