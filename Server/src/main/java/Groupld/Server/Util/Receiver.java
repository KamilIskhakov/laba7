package Groupld.Server.Util;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.Serializer;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Server;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Receiver {
    private final int bufferSize;
    private final DatagramSocket server;
    private final Logger logger;

    public Receiver(DatagramSocket server, int bufferSize, Logger logger) {
        this.server = server;
        this.bufferSize = bufferSize;
        this.logger = logger;
    }

    public void receive() throws IOException, ClassNotFoundException {
        byte[] bytesReceiving = new byte[bufferSize];
        DatagramPacket request = new DatagramPacket(bytesReceiving, bytesReceiving.length);
        server.receive(request);
        RequestDTO received = (RequestDTO) Serializer.deserialize(bytesReceiving);
        InetAddress client = request.getAddress();
        int port = request.getPort();
        logger.info("received request from address " + client + ", port " + port);
        ServerResponse response = Server.serverRequestFromClientManager.getServerResponse(received);
        byte[] bytesSending = Serializer.serialize(response);
        DatagramPacket packet = new DatagramPacket(bytesSending, bytesSending.length, client, port);
        server.send(packet);
        logger.info("response sent to the address " + client + ", port " + port);
    }
}