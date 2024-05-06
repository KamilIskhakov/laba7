package Server.Util;

import Controler.RequestFactory.*;
import Controler.ChannelClientServerUtil.ServerResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HandlerServerMap {
        private Map<Class<? extends Request>, HandlersServer<? extends Request>> handlerMap = new HashMap<>();

        public <T extends Request> void register(Class<T> event, Function<T, ServerResponse> handler) {
            HandlersServer<T> list;
            if (!handlerMap.containsKey(event)) {
                list = new HandlersServer<T>();
                handlerMap.put(event, list);
            } else {
                list = getHandlers(event);
            }
            list.add(handler);
        }
    public <T extends Request> HandlersServer getHandlers(Class<T> event) {
        return handlerMap.get(event);
    }


}
