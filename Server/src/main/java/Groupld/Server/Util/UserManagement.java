package Groupld.Server.Util;

import Groupld.Server.usersmanagers.SQLUserManager;
import Groupld.Server.usersmanagers.tablecreators.SQLUserTableCreator;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

public class UserManagement {
    private final Logger logger;
    private final SQLUserManager sqlUserManager;
    private final JWTService jwtService;
    private final UserTokenPolice userTokenPolice;
    private final UsersHandler usersHandler;

    public UserManagement(Connection connection, Logger logger) throws SQLException {
        this.logger = logger;
        SQLUserTableCreator sqlUserTableCreator = new SQLUserTableCreator(connection, "users_authorization", logger);
        this.sqlUserManager = new SQLUserManager(new ReentrantLock(), sqlUserTableCreator.init(), connection, "users_authorization", logger);
        this.jwtService = new JWTService();
        this.userTokenPolice = new UserTokenPolice(logger, jwtService);
        this.usersHandler = new UsersHandler(sqlUserManager, logger, jwtService);
    }

    public UsersHandler getUsersHandler() {
        return usersHandler;
    }

    public UserTokenPolice getUserTokenPolice() {
        return userTokenPolice;
    }
}

