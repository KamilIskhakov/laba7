package Groupld.Server.Util;


import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Controler.RequestFactoryDTO.*;
import Groupld.Server.ConcreteCommands.*;


public class ServerHandlerRequestManager {
    private HandlerServerMap handlerServerMap;
    public ServerHandlerRequestManager(){
        handlerServerMap = new HandlerServerMap();
        handlerServerMap.register(AddRequestDTO.class, (event) -> new AddCommand(event).execute());
        handlerServerMap.register(ClearRequestDTO.class, (event) -> new AddCommand(event).execute());
        /*handlerServerMap.register(ExecuteScriptRequestDTO.class, (event) -> new ExecuteScriptCommand(event).execute());
        handlerServerMap.register(ExitRequestDTO.class, (event) -> new ExitCommand(event).execute());
        handlerServerMap.register(FilterGreaterThanHeightRequestDTO.class, (event) -> new FilterGreaterThanHeightCommand(event).execute());
        handlerServerMap.register(FilterLessThanLocationRequestDTO.class, (event) -> new FilterLessThanLocationCommand(event).execute());
        handlerServerMap.register(GroupCountingByNameRequestDTO.class, (event) -> new GroupCountingByNameCommand(event).execute());
        handlerServerMap.register(HeadRequestDTO.class, (event) -> new HeadCommand(event).execute());
        handlerServerMap.register(HelpRequestDTO.class, (event) -> new HelpCommand(event).execute());
        handlerServerMap.register(InfoRequestDTO.class, (event) -> new InfoCommand(event).execute());
        handlerServerMap.register(RemoveByIdRequestDTO.class, (event) -> new RemoveByIdCommand(event).execute());
        handlerServerMap.register(RemoveHeadRequestDTO.class, (event) -> new RemoveHeadCommand(event).execute());
        handlerServerMap.register(ShowRequestDTO.class, (event) -> new ShowCommand(event).execute());
        handlerServerMap.register(UpdateRequestDTO.class, (event) -> new UpdateCommand(event).execute());*/
    }
    public ServerResponse getServerResponse(ReceivedData request){
        return handlerServerMap.getHandlers(request).handleEvent(request);
    }
}

