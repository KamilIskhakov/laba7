package Controler.Commands;

import Client.Client;
import Controler.Command;
import Controler.RequestToServer.ExecuteCode;
import Controler.RequestToServer.ServerResponse;
import Server.Server;

public class GroupCountingByNameCommand implements Command {

    @Override
    public String getDescription() {
        return getName() + "сгруппировать элементы коллекции по значению поля name";
    }

    @Override
    public String getName() {
        return "group_counting_by_name ";
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
        return new ServerResponse(s,ExecuteCode.VALUE);
    }

}
