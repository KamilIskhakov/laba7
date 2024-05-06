package Server.Util;

import Controler.RequestFactory.Request;
import Controler.ChannelClientServerUtil.ServerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HandlersServer<T extends Request> {
    private List<Function<T, ServerResponse>> backingList = new ArrayList<>();

    public void add(Function<T, ServerResponse> handler) {
        backingList.add(handler);
    }

    public void remove(Function<T, ServerResponse> handler) {
        backingList.remove(handler);
    }

    public ServerResponse handleEvent(T event) {
        for (Function<T, ServerResponse> handle : backingList) {
            return handle.apply(event);
        }
        return null;
    }
}