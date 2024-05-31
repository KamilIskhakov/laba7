package Groupld.Server.ConcreteCommands;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Controler.RequestFactoryDTO.AddRequestDTO;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Command;
import Groupld.Server.Server;
import Groupld.Server.Util.ReceivedData;

public class AddCommand implements Command {
    private ReceivedData request;

    public AddCommand(ReceivedData request){
        this.request = request;
    }

    @Override
    public String getDescription() {
        return getName() + "добавляет новый объект в конец коллекции";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public ServerResponse execute(){
        /*Server.sqlCollectionManager.addToCollection(request.getPerson());*/
        return null;/*new ServerResponse(getName(), request.getToken());*/
    }
}
