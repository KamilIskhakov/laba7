package Groupld.Server.Util;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;

import java.net.InetAddress;

public class ReceivedData {
    private final RequestDTO request;
    private final InetAddress client;
    private final int port;
    private String username;

    public ReceivedData(RequestDTO request, InetAddress client, int port) {
        this.request = request;
        this.client = client;
        this.port = port;
    }

    public RequestDTO getRequest() {
        return request;
    }

    public InetAddress getClient() {
        return client;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
