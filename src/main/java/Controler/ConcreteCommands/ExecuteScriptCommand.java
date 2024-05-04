package Controler.ConcreteCommands;

import Controler.Command;
import Controler.RequestToServer.ServerResponse;

public class ExecuteScriptCommand implements Command {
    private String filepath;
    public ExecuteScriptCommand(String filepath){
        this.filepath = filepath;
    }
    @Override
    public String getDescription() {
        return getName() + "считывает скрипт пользователя";
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public ServerResponse execute() {
        return new ServerResponse(getName(),filepath);
    }


}
