package Controler;

import Client.Client;
import java.util.HashMap;
import Controler.Handlers.*;
import Exceptions.NotCorrectException;

public class CommandRequestManager {
    private HashMap<String, Handler> commands;

    public CommandRequestManager() {
        commands = new HashMap<>();
        commands.put("help", new HelpHandler());
        commands.put("info", new InfoHandler());
        commands.put("save", new SaveHandler());
        commands.put("show", new ShowHandler());
        commands.put("add", new AddHandler());
        commands.put("update", new UpdateHandler());
        commands.put("remove_by_ID", new RemoveByIdHandler());
        commands.put("clear", new ClearHandler());
        commands.put("execute_script", new ExecuteScriptHandler());
        commands.put("head", new HeadHandler());
        commands.put("remove_head", new RemoveHeadHandler());
        commands.put("group_counting_by_name", new GroupCountingByNameHandler());
        commands.put("filter_greater_than_height", new FilterGreaterThanHeightHandler());
        commands.put("filter_less_than_location", new FilterLessThanLocationHandler());
        commands.put("exit", new ExitHandler());
    }

    public Handler preparationForShipment(String commandName, String arguments){
        try {
            Handler handler = commands.get(commandName);
            handler.handle(arguments);
            return handler;
        } catch (NullPointerException | NotCorrectException exp) {
            Client.terminalOutput.println("Некорректный ввод команды");
        }
        return null;
    }
}
