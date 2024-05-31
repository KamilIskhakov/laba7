package Groupld.Server.Util;

import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HandlerServerMap {
    private Map<Class<? extends RequestDTO>, HandlersServer<? extends ReceivedData>> handlerMap = new HashMap<>();

    public <T extends RequestDTO> void register(Class<T> event, Function<ReceivedData, ServerResponse> handler) {
        HandlersServer<ReceivedData> list;
        if (!handlerMap.containsKey(event)) {
            list = new HandlersServer<ReceivedData>();
            handlerMap.put(event, list);
        } else {
            list = getHandlers(event);
        }
        list.add(handler);
    }
    public <T extends RequestDTO> HandlersServer getHandlers(ReceivedData event) {
        return handlerMap.get(event.getRequest().getClass());
    }
    public <T extends RequestDTO> HandlersServer getHandlers(Class<T> event) {
        return handlerMap.get(event);
    }



}

