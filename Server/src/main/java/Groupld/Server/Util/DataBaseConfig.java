package Groupld.Server.Util;

import Groupld.Controler.CollectionObjects.Coordinates;
import Groupld.Controler.CollectionObjects.Location;
import Groupld.Controler.CollectionObjects.Person;
import Groupld.Server.Server;
import Groupld.Server.collectionmanagers.DAO.PersonDAO;
import Groupld.Server.collectionmanagers.User;
import Groupld.Server.collectionmanagers.UserPerson;
import Groupld.Server.collectionmanagers.DAO.UserPersonDAO;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConfig {
    private static final int INDEX_DB_HOSTNAME = 2;
    private static final int INDEX_DB_PORT = 3;
    private static final int INDEX_DB_NAME = 4;
    private static final int INDEX_DB_USERNAME = 5;
    private static final int INDEX_DB_PASSWORD = 6;
    private final String[] args;
    private Configuration configuration;
    private SessionFactory sessionFactory;

    public DataBaseConfig(String[] args) {
        this.args = args;
    }

    public void configureHibernate() {
        String dataBaseUrl = "jdbc:postgresql://" + args[INDEX_DB_HOSTNAME] + ":" + args[INDEX_DB_PORT] + "/" + args[INDEX_DB_NAME];
        configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", dataBaseUrl);
        configuration.setProperty("hibernate.connection.username", args[INDEX_DB_USERNAME]);
        configuration.setProperty("hibernate.connection.password", args[INDEX_DB_PASSWORD]);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");

        configuration.addAnnotatedClass(Person.class);
        configuration.addAnnotatedClass(Location.class);
        configuration.addAnnotatedClass(Coordinates.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(UserPerson.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public PersonDAO createPersonDAO() {
        return new PersonDAO(sessionFactory, Server.LOGGER);
    }

    public UserPersonDAO createUserPersonDAO() {
        return new UserPersonDAO(sessionFactory);
    }

    public String getDatabaseUrl() {
        return "jdbc:postgresql://" + args[INDEX_DB_HOSTNAME] + ":" + args[INDEX_DB_PORT] + "/" + args[INDEX_DB_NAME];
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getDatabaseUrl(), args[INDEX_DB_USERNAME], args[INDEX_DB_PASSWORD]);
    }
}

