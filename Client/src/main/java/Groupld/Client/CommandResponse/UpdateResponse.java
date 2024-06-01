package Groupld.Client.CommandResponse;

import Groupld.Client.Client;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;

public class UpdateResponse implements Response {
    @Override
    public void open(ServerResponse serverResponse) {
        if (serverResponse.getMessage() != null){
            Client.terminalOutput.println(serverResponse.getMessage());
        }else{
        Client.terminalOutput.printlnWellDoneCommandMessage();}
    }
}
