package Server;

import Controler.RequestToServer.Checker;
import Exceptions.IllegalAddressException;
import Server.Response.Receiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public final class Server {
    public static CollectionManager collectionManager;
    private static final int BUFFER_SIZE = 2048;
    private static final Logger LOGGER = LogManager.getLogger(Server.class);
    private static final int NUMBER_OF_ARGUMENTS = 2;

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        LOGGER.trace("the server is running");
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try {
            // имя хоста указывается первой строкой аргументах командной строки, порт – второй
            final InetSocketAddress address = Checker.checkAddress(args[0], args[1]);
            LOGGER.info(() -> "set " + address + " address");
            collectionManager = CollectionCreator.load("save.xml");
                try {
                    DatagramSocket server = new DatagramSocket(address);
                    Receiver receiver = new Receiver(server, BUFFER_SIZE, LOGGER);
                    while (true) {
                        receiver.receive();
                    }
                } catch (ClassNotFoundException e) {
                    LOGGER.error("wrong data from client");
                } catch (IOException e) {
                    LOGGER.error(e);
                }

        } catch (IllegalAddressException e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            LOGGER.error("command line arguments must indicate host name and port");
        }
        LOGGER.trace("the server is shutting down");
    }
}
