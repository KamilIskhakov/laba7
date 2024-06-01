package Groupld.Server;

import Groupld.Controler.ChannelClientServerUtil.Checker;
import Groupld.Controler.CollectionObjects.Coordinates;
import Groupld.Controler.CollectionObjects.Location;
import Groupld.Controler.CollectionObjects.Person;
import Groupld.Controler.Exceptions.IllegalAddressException;
import Groupld.Server.Util.*;
import Groupld.Server.collectionmanagers.*;
import Groupld.Server.collectionmanagers.DAO.PersonDAO;
import Groupld.Server.collectionmanagers.DAO.UserPersonDAO;
import Groupld.Server.collectionmanagers.User;
import Groupld.Server.usersmanagers.SQLUserManager;
import Groupld.Server.usersmanagers.tablecreators.SQLUserTableCreator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

public final class Server {

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
    private static final String USER_TABLE_NAME = "users_authorization";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ExecutorService REQUEST_READING_POOL = new ForkJoinPool();
    private static final ExecutorService REQUEST_PROCESSING_POOL = new ForkJoinPool();
    private static final ExecutorService RESPONSE_SENDING_POOL = new ForkJoinPool();
    public static ServerHandlerRequestManager serverHandlerRequestManager;
    public static  PersonRepository personRepository;


    private Server() {
        throw new UnsupportedOperationException("This is a utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        LOGGER.info("The server is running");
        if (args.length == NUMBER_OF_ARGUMENTS) {
            try {
                // имя хоста указывается первой строкой аргумента командной строки, порт – второй
                final InetSocketAddress address = Checker.checkAddress(args[INDEX_HOST], args[INDEX_PORT]);
                LOGGER.info(() -> "Set " + address + " address");

                final String dataBaseUrl = "jdbc:postgresql://" + args[INDEX_DB_HOSTNAME] + ":" + args[INDEX_DB_PORT] + "/" + args[INDEX_DB_NAME];
                final String dataBaseUsername = args[INDEX_DB_USERNAME];
                final String dataBasePassword = args[INDEX_DB_PASSWORD];

                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.url", dataBaseUrl);
                configuration.setProperty("hibernate.connection.username", dataBaseUsername);
                configuration.setProperty("hibernate.connection.password", dataBasePassword);
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                configuration.setProperty("hibernate.hbm2ddl.auto", "update"); // Or other schema management option
                configuration.setProperty("hibernate.show_sql", "true");

                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(Location.class);
                configuration.addAnnotatedClass(Coordinates.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(UserPerson.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                PersonDAO personDAO = new PersonDAO(sessionFactory,LOGGER);
                UserPersonDAO userPersonDAO = new UserPersonDAO(sessionFactory);
                personRepository = new PersonRepository(personDAO, userPersonDAO);
                serverHandlerRequestManager = new ServerHandlerRequestManager();

                LOGGER.info(() -> "Connected to the database " + dataBaseUrl);

                try (Connection connection = DriverManager.getConnection(dataBaseUrl, dataBaseUsername, dataBasePassword);
                     DatagramSocket server = new DatagramSocket(address)) {
                    LOGGER.info(() -> "Opened datagram socket on the address " + address);

                    SQLUserTableCreator sqlUserTableCreator = new SQLUserTableCreator(connection, USER_TABLE_NAME, LOGGER);
                    SQLUserManager sqlUserManager = new SQLUserManager(new ReentrantLock(), sqlUserTableCreator.init(), connection, USER_TABLE_NAME, LOGGER);
                    JWTService jwtService = new JWTService();
                    UserTokenPolice userTokenPolice = new UserTokenPolice(LOGGER, jwtService);
                    UsersHandler usersHandler = new UsersHandler(sqlUserManager, LOGGER, jwtService);
                    Receiver receiver = new Receiver(server, BUFFER_SIZE, LOGGER, usersHandler, userTokenPolice);
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
                } catch (IOException | SQLException  e) {
                    e.printStackTrace();
                    LOGGER.error(e);
                }
            } catch (IllegalAddressException e) {
                LOGGER.error(e.getMessage());
            }
        } else {
            LOGGER.error("Command line arguments must indicate host name and port");
        }
        LOGGER.trace("The server is shutting down");
    }

    private static boolean exitFromConsole() {
        if (!SCANNER.hasNext()) {
            return false;
        }
        String command = SCANNER.nextLine().toLowerCase(Locale.ROOT);
        if ("exit".equals(command)) {
            return true;
        }
        System.out.println("Unknown command");
        return false;
    }
}