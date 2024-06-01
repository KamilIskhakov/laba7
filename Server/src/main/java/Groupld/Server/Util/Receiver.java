package Groupld.Server.Util;

import Groupld.Controler.PullingRequest;
import Groupld.Controler.PullingResponse;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.ChannelClientServerUtil.Serializer;
import Groupld.Controler.Response;
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
    private final UserTokenPolice userTokenPolice;

    public Receiver(DatagramSocket server, int bufferSize, Logger logger, UsersHandler usersHandler, UserTokenPolice userTokenPolice) {
        this.server = server;
        this.bufferSize = bufferSize;
        this.logger = logger;
        this.usersHandler = usersHandler;
        this.userTokenPolice = userTokenPolice;
    }
    public void start(ExecutorService requestReadingPool, ExecutorService requestProcessingPool,
                      ExecutorService responseSendingPool) {
        requestReadingPool.submit(() -> {
            while (!server.isClosed()) {
                ReceivedData receivedData = receive();
                InetAddress client = receivedData.getClient();
                int port = receivedData.getPort();
                requestProcessingPool.submit(() -> {
                    Object response = processRequest(receivedData);
                    responseSendingPool.submit(() -> sendResponse(response, client, port));
                });
            }
        });
    }
    private Object processRequest(ReceivedData received) {
        if (received.getRequest() instanceof PullingRequest) {
            return usersHandler.authorization((PullingRequest) received.getRequest());
        } else {
            if (userTokenPolice.verify(received)){
                logger.info("start to process request");
                return Server.serverHandlerRequestManager.getServerResponse(received);
            }else {
                return new PullingResponse("ahaha",null);
            }
        }
    }

    private boolean sendResponse(Object response, InetAddress client, int port) {
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