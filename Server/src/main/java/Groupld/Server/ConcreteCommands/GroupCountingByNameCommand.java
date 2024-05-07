package Groupld.Server.ConcreteCommands;

import Groupld.Controler.RequestFactoryDTO.GroupCountingByNameRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;

public class GroupCountingByNameCommand implements Command {
    private GroupCountingByNameRequestDTO request;

    public GroupCountingByNameCommand(RequestDTO request){
        this.request = (GroupCountingByNameRequestDTO) request;
    }

    @Override
    public String getDescription() {
        return getName() + "сгруппировать элементы коллекции по значению поля name";
    }

    @Override
    public String getName() {
        return "group_counting_by_name";
    }

    @Override
    public ServerResponse execute() {
        String s = "";
        int count = 0;
        for(int i : Server.collectionManager.GroupPeople())   {
            if (i>0){
            s += "Группа имён с длинной " + count + ": " + i + "\n";
            }
            count += 1;
        }
        return new ServerResponse(getName(),s);
    }

}
