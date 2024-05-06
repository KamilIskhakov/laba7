package Server.Util;

import Controler.RequestFactory.*;
import Controler.RequestFactory.Request;
import Server.ConcreteCommands.*;
import Controler.ChannelClientServerUtil.ServerResponse;

public class ServerRequestFromClientManager {
    private HandlerServerMap handlerServerMap;
    public ServerRequestFromClientManager(){
        handlerServerMap = new HandlerServerMap();
        handlerServerMap.register(AddRequest.class, (event) -> new AddCommand(event).execute());
        handlerServerMap.register(ClearRequest.class, (event) -> new AddCommand(event).execute());
        handlerServerMap.register(ExecuteScriptRequest.class, (event) -> new ExecuteScriptCommand(event).execute());
        handlerServerMap.register(ExitRequest.class, (event) -> new ExitCommand(event).execute());
        handlerServerMap.register(FilterGreaterThanHeightRequest.class, (event) -> new FilterGreaterThanHeightCommand(event).execute());
        handlerServerMap.register(FilterLessThanLocationRequest.class, (event) -> new FilterLessThanLocationCommand(event).execute());
        handlerServerMap.register(GroupCountingByNameRequest.class, (event) -> new GroupCountingByNameCommand(event).execute());
        handlerServerMap.register(HeadRequest.class, (event) -> new HeadCommand(event).execute());
        handlerServerMap.register(HelpRequest.class, (event) -> new HelpCommand(event).execute());
        handlerServerMap.register(InfoRequest.class, (event) -> new InfoCommand(event).execute());
        handlerServerMap.register(RemoveByIdRequest.class, (event) -> new RemoveByIdCommand(event).execute());
        handlerServerMap.register(RemoveHeadRequest.class, (event) -> new RemoveHeadCommand(event).execute());
        handlerServerMap.register(ShowRequest.class, (event) -> new ShowCommand(event).execute());
        handlerServerMap.register(UpdateRequest.class, (event) -> new UpdateCommand(event).execute());
    }
    public ServerResponse getServerResponse(Request request){
        return handlerServerMap.getHandlers(request.getClass()).handleEvent(request);
    }
}

