package Server.Response;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Client.CommandFactory.Handler;
import Controler.Command;
import Controler.RequestToServer.Serializer;
import Controler.RequestToServer.ServerResponse;
import org.apache.logging.log4j.Logger;

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
        Command received = (Command) Serializer.deserialize(bytesReceiving);
        InetAddress client = request.getAddress();
        int port = request.getPort();
        logger.info("received request from address " + client + ", port " + port);
        ServerResponse response = received.execute();
        byte[] bytesSending = Serializer.serialize(response);
        DatagramPacket packet = new DatagramPacket(bytesSending, bytesSending.length, client, port);
        server.send(packet);
        logger.info("response sent to the address " + client + ", port " + port);
    }
}