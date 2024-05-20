package Groupld.Server.Util;

import Groupld.Controler.PullingRequest;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.Serializer;
import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Server.Server;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;

public class Receiver {
    private final int bufferSize;
    private final DatagramSocket server;
    private final Logger logger;
    private final UsersHandler usersHandler;

    public Receiver(DatagramSocket server, int bufferSize, Logger logger, UsersHandler usersHandler) {
        this.server = server;
        this.bufferSize = bufferSize;
        this.logger = logger;
        this.usersHandler = usersHandler;
    }
    public void start(ExecutorService requestReadingPool, ExecutorService requestProcessingPool,
                      ExecutorService responseSendingPool) {
        requestReadingPool.submit(() -> {
            while (!server.isClosed()) {
                ReceivedData receivedData = receive();
                RequestDTO request = receivedData.getRequest();
                InetAddress client = receivedData.getClient();
                int port = receivedData.getPort();
                requestProcessingPool.submit(() -> {
                    ServerResponse response = processRequest(request);
                    responseSendingPool.submit(() -> sendResponse(response, client, port));
                });
            }
        });
    }
    private ServerResponse processRequest(RequestDTO received) {
        if (received instanceof PullingRequest) {
            return usersHandler.authorization((PullingRequest) received);
        } else {
            return usersHandler.handle(received);
            }
    }

    private boolean sendResponse(ServerResponse response, InetAddress client, int port) {
        try {
            byte[] bytesSending = Serializer.serialize(response);
            DatagramPacket packet = new DatagramPacket(bytesSending, bytesSending.length, client, port);
            server.send(packet);
            logger.info("response sent to the address " + client + ", port " + port);
        } catch (IOException e) {
            logger.error("error during sending response", e);
        }
        return true;
    }

    public ReceivedData receive() {
        ReceivedData receivedData = null;
        try {
            byte[] bytesReceiving = new byte[bufferSize];
            DatagramPacket request = new DatagramPacket(bytesReceiving, bytesReceiving.length);
            server.receive(request);
            RequestDTO received = (RequestDTO) Serializer.deserialize(bytesReceiving);
            InetAddress client = request.getAddress();
            int port = request.getPort();
            receivedData = new ReceivedData(received, client, port);
            logger.info(() -> "received request from address " + client + ", port " + port);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("error during reading response", e);
        }
        return receivedData;
    }
}