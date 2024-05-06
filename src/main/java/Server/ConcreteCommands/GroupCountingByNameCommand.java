package Server.ConcreteCommands;

import Controler.RequestFactory.GroupCountingByNameRequest;
import Controler.RequestFactory.Request;
import Server.Command;
import Controler.ChannelClientServerUtil.ServerResponse;
import Server.Server;

public class GroupCountingByNameCommand implements Command {
    private GroupCountingByNameRequest request;

    public GroupCountingByNameCommand(Request request){
        this.request = (GroupCountingByNameRequest) request;
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
