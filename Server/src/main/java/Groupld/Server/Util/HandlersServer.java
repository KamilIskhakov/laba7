package Groupld.Server.Util;

import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HandlersServer<T extends ReceivedData> {
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
