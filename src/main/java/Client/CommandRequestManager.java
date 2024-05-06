package Client;

import Client.RequestFactory.*;
import Client.RequestFactoryDTO.RequestDTO;
import Controler.Exceptions.NotCorrectException;

import java.util.HashMap;

public class CommandRequestManager {
    private HashMap<String, Request> request;

    public CommandRequestManager() {
        request = new HashMap<>();
        request.put("help", new HelpRequest());
        request.put("info", new InfoRequest());
        request.put("save", new SaveRequest());
        request.put("show", new ShowRequest());
        request.put("add", new AddRequest());
        request.put("update", new UpdateRequest());
        request.put("remove_by_ID", new RemoveByIdRequest());
        request.put("clear", new ClearRequest());
        request.put("execute_script", new ExecuteScriptRequest());
        request.put("head", new HeadRequest());
        request.put("remove_head", new RemoveHeadRequest());
        request.put("group_counting_by_name", new GroupCountingByNameRequest());
        request.put("filter_greater_than_height", new FilterGreaterThanHeightRequest());
        request.put("filter_less_than_location", new FilterLessThanLocationRequest());
        request.put("exit", new ExitRequest());
    }

    public RequestDTO preparationForShipment(String commandName, String arguments){
        try {
            Request request = this.request.get(commandName);
            return request.reque(arguments);
        } catch (NullPointerException | NotCorrectException exp) {
            Client.terminalOutput.println("Некорректный ввод команды");
            return null;
        }
    }
}
