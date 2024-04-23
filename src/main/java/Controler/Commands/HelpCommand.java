package Controler.Commands;

import Client.Client;
import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

import java.io.File;
import java.util.ArrayList;

public class HelpCommand implements Command {

    @Override
    public String getDescription() {
        return getName() + "вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help ";
    }

    @Override
    public ServerResponse  execute() {
        ArrayList<String[]> help_out = new ArrayList<>();
        File directory = new File("src/main/java/Controler/Commands"); //ссылка папку, в которой хранятся все команды
        String[] commandClasses = directory.list(); //массив из названий всех элементов в папке

        for (String filename : commandClasses) {
            if (filename.endsWith(".java")) {
                String[] com_desc = new String[2];
                com_desc[0] = filename.replace(".java", "").toLowerCase(); //название команды
                try {
                    Class cls = Class.forName("Commands." + filename.replace(".java", ""));
                    Command commandClassObject = (Command) cls.getConstructor().newInstance();
                    com_desc[1] = commandClassObject.getDescription();
                } catch (Exception ignored) {
                }
                help_out.add(com_desc);
            }
        }
        String output = "";
        output += "Доступные команды:\n";
        for (String[] comhelp : help_out) {
            output += "   " + comhelp[0] + " : " + comhelp[1] + "\n";
        }
        return new ServerResponse(output,ExecuteCode.VALUE);
    }
}
