package Client;

import Client.CommandResponse.*;
import Controler.ChannelClientServerUtil.ServerResponse;

import java.util.HashMap;

public class CommandResponseFromServerManager {
    private HashMap<String, Response> response;

    public CommandResponseFromServerManager() {
        response = new HashMap<>();
        response.put("help", new HelpResponse());
        response.put("info", new InfoResponse());
        response.put("show", new ShowResponse());
        response.put("add", new AddResponse());
        response.put("update", new UpdateResponse());
        response.put("remove_by_ID", new RemoveByIdResponse());
        response.put("clear", new ClearResponse());
        response.put("execute_script", new ExecuteScriptResponse());
        response.put("head", new HeadResponse());
        response.put("remove_head", new RemoveHeadResponse());
        response.put("group_counting_by_name", new GroupCountingByNameResponse());
        response.put("filter_greater_than_height", new FilterGreaterThanHeightResponse());
        response.put("filter_less_than_location", new FilterLessThanLocationResponse());
        response.put("exit", new ExitResponse());
    }

    public void preparationForOutput(ServerResponse serverResponse){
        try {
            Response res = response.get(serverResponse.getCommandName());
            res.open(serverResponse);
        } catch (NullPointerException exp) {
            Client.terminalOutput.println("Некорректный ввод команды");
        }
    }
}
