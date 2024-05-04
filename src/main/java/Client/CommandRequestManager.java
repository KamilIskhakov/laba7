package Client;

import java.util.HashMap;
import Client.CommandFactory.*;
import Controler.Command;
import Controler.Exceptions.NotCorrectException;

public class CommandRequestManager {
    private HashMap<String, Handler> request;

    public CommandRequestManager() {
        request = new HashMap<>();
        request.put("help", new HelpHandler());
        request.put("info", new InfoHandler());
        request.put("save", new SaveHandler());
        request.put("show", new ShowHandler());
        request.put("add", new AddHandler());
        request.put("update", new UpdateHandler());
        request.put("remove_by_ID", new RemoveByIdHandler());
        request.put("clear", new ClearHandler());
        request.put("execute_script", new ExecuteScriptHandler());
        request.put("head", new HeadHandler());
        request.put("remove_head", new RemoveHeadHandler());
        request.put("group_counting_by_name", new GroupCountingByNameHandler());
        request.put("filter_greater_than_height", new FilterGreaterThanHeightHandler());
        request.put("filter_less_than_location", new FilterLessThanLocationHandler());
        request.put("exit", new ExitHandler());
    }

    public Command preparationForShipment(String commandName, String arguments){
        try {
            Handler handler = request.get(commandName);
            handler.handle(arguments);
            return handler.getCommand();
        } catch (NullPointerException | NotCorrectException exp) {
            Client.terminalOutput.println("Некорректный ввод команды");
            return null;
        }
    }
}
