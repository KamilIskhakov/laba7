package Groupld.Server;

import Groupld.Controler.ChannelClientServerUtil.Checker;
import Groupld.Controler.Exceptions.IllegalAddressException;
import Groupld.Server.Util.JWTService;
import Groupld.Server.Util.ServerRequestFromClientManager;
import Groupld.Server.Util.Receiver;
import Groupld.Server.Util.UsersHandler;
import Groupld.Server.collectionmanagers.CollectionManager;
import Groupld.Server.collectionmanagers.SQLCollectionManager;
import Groupld.Server.collectionmanagers.datamanagers.SQLDataManager;
import Groupld.Server.usersmanagers.SQLUserManager;
import Groupld.Server.usersmanagers.tablecreators.SQLUserTableCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

public final class Server {

    public static CollectionManager sqlCollectionManager;
    private static final int BUFFER_SIZE = 2048;
    public static final Logger LOGGER = LogManager.getLogger(Server.class);
    private static final int NUMBER_OF_ARGUMENTS = 7;
    private static final int INDEX_HOST = 0;
    private static final int INDEX_PORT = 1;
    private static final int INDEX_DB_HOSTNAME = 2;
    private static final int INDEX_DB_PORT = 3;
    private static final int INDEX_DB_NAME = 4;
    private static final int INDEX_DB_USERNAME = 5;
    private static final int INDEX_DB_PASSWORD = 6;
    private static final String USER_TABLE_NAME = "users";
    private static final String DATA_TABLE_NAME = "people";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ExecutorService REQUEST_READING_POOL = ForkJoinPool.commonPool();
    private static final ExecutorService REQUEST_PROCESSING_POOL = ForkJoinPool.commonPool();
    private static final ExecutorService RESPONSE_SENDING_POOL = ForkJoinPool.commonPool();
    public static ServerRequestFromClientManager serverRequestFromClientManager;

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        LOGGER.info("the server is running");
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try {
            // имя хоста указывается первой строкой аргумента командной строки, порт – второй
            final InetSocketAddress address = Checker.checkAddress(args[INDEX_HOST], args[INDEX_PORT]);
            LOGGER.info(() -> "set " + address + " address");
            // jdbc:postgresql://localhost:5432/postgres jdbc:postgresql://localhost:5432/postgres
            final String dataBaseUrl = "jdbc:postgresql://" + args[INDEX_DB_HOSTNAME] + ":" + args[INDEX_DB_PORT] + "/" + args[INDEX_DB_NAME];
            final String dataBaseUsername = args[INDEX_DB_USERNAME];
            final String dataBasePassword = args[INDEX_DB_PASSWORD];
                try (Connection connection = DriverManager.getConnection(dataBaseUrl, dataBaseUsername, dataBasePassword);
                     DatagramSocket server = new DatagramSocket(address)) {
                    LOGGER.info(() -> "connected to the database " + dataBaseUrl);
                    LOGGER.info(() -> "opened datagram socket on the address " + address);
                    SQLDataManager sqlDataManager = new SQLDataManager(connection, DATA_TABLE_NAME, USER_TABLE_NAME, LOGGER);
                    SQLUserTableCreator sqlUserTableCreator = new SQLUserTableCreator(connection, USER_TABLE_NAME, LOGGER);
                    SQLUserManager sqlUserManager = new SQLUserManager(new ReentrantLock(), sqlUserTableCreator.init(), connection, USER_TABLE_NAME, LOGGER);
                    sqlCollectionManager = new SQLCollectionManager(sqlDataManager.initCollection(), sqlDataManager);
                    serverRequestFromClientManager = new ServerRequestFromClientManager();
                    JWTService jwtService = new JWTService();
                    UsersHandler usersHandler = new UsersHandler(sqlUserManager, LOGGER, jwtService);
                    Receiver receiver = new Receiver(server, BUFFER_SIZE, LOGGER, usersHandler);
                    receiver.start(REQUEST_READING_POOL, REQUEST_PROCESSING_POOL, RESPONSE_SENDING_POOL);

                    while (true) {
                        if (exitFromConsole()) {
                            REQUEST_READING_POOL.shutdown();
                            REQUEST_PROCESSING_POOL.shutdown();
                            RESPONSE_SENDING_POOL.shutdown();
                            break;
                        }
                        receiver.receive();
                    }
                }catch (IOException | SQLException e) {
                    e.printStackTrace();
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
    private static boolean exitFromConsole() {
        if (!SCANNER.hasNext()) {
            return false;
        }
        String command = SCANNER.nextLine().toLowerCase(Locale.ROOT);
        if ("exit".equals(command)) {
            return true;
        }
        System.out.println("unknown command");
        return false;
    }
}
