package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.HeadRequestDTO;
import Groupld.Controler.RequestFactoryDTO.HelpRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Util.ReceivedData;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;

public class HelpCommand implements Command {
    private HelpRequestDTO request;
    private ReceivedData receivedData;
    public HelpCommand(){}

    public HelpCommand(ReceivedData request){

        this.request = (HelpRequestDTO) request.getRequest();
        this.receivedData = request;

    }


    @Override
    public String getDescription() {
        return getName() + " вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public ServerResponse execute() {
        ArrayList<String[]> help_out = new ArrayList<>();
        File directory = new File("/Users/kamiliskhakov/IdeaProjects/laba7/Server/src/main/java/Groupld/Server/ConcreteCommands"); //ссылка папку, в которой хранятся все команды
        String[] commandClasses = directory.list(); //массив из названий всех элементов в папке

        for (String filename : commandClasses) {
            if (filename.endsWith(".java")) {
                String[] com_desc = new String[2];
                com_desc[0] = filename.replace(".java", "").toLowerCase(); //название команды
                try {
                    Class cls = Class.forName("Groupld.Server.ConcreteCommands." + filename.replace(".java", ""));
                    Command commandClassObject = (Command) cls.newInstance();
                    com_desc[0] = commandClassObject.getName();
                    com_desc[1] = commandClassObject.getDescription();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                help_out.add(com_desc);
            }
        }
        String output = "";
        output += "Доступные команды:\n";
        for (String[] comhelp : help_out) {
            output += comhelp[1] + "\n";
        }
        return new ServerResponse(getName(),output, request.getToken());
    }
}
