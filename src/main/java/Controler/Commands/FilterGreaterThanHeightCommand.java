package Controler.Commands;

import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class FilterGreaterThanHeightCommand implements Command {
    private Integer argument;
    public FilterGreaterThanHeightCommand(Integer argument){
        this.argument = argument;
    }
    @Override
    public String getDescription() {
        return getName() + "выводит объекты коллекции, у которых поле height меньше заданного";
    }

    @Override
    public String getName() {
        return "filter_greater_than_height ";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(Server.collectionManager.FilterGreaterThanHeight(argument),ExecuteCode.VALUE);
    }

}
