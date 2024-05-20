package Groupld.Server.Util;

import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Controler.PullingRequest;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Server.PasswordEncoder;
import Groupld.Server.Server;
import Groupld.Server.usersmanagers.SQLUserManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class UsersHandler {
    private final SQLUserManager sqlUserManager;
    private final Logger logger;

    public UsersHandler(SQLUserManager sqlUserManager, Logger logger) {
        this.sqlUserManager = sqlUserManager;
        this.logger = logger;
    }
    public ServerResponse authorization(PullingRequest request) {
        User newUser = new User(request.getUsername(), PasswordEncoder.encode(request.getPassword()));
        if (sqlUserManager.isUsernameExists(newUser.getUsername())) {
            if (sqlUserManager.checkPassword(newUser)) {
                logger.info(() -> "user " + newUser.getUsername() + " authorized");
                return Server.serverRequestFromClientManager.getServerResponse(request);
            } else {
                logger.info("failed login attempt");
                return Server.serverRequestFromClientManager.getServerResponse(request);
            }
        } else {
            sqlUserManager.registerUser(newUser);
            logger.info(() -> "user " + newUser.getUsername() + " registered");
            return Server.serverRequestFromClientManager.getServerResponse(request);
        }
    }

    public ServerResponse handle(RequestDTO request) {
        User tockenPolicy = new User(JWTTocken jwttocken);
        if (tockenPolicy.norm()) {
            if (tockenPolicy.notFinished()) {
                logger.info(() -> "user " + tockenPolicy.getUserID() + " authorized");
                return Server.serverRequestFromClientManager.getServerResponse(request);
            } else {
                logger.info("user " + tockenPolicy.getUserID() + " has time out tocken");
                return new PullingResponse(RegistrationCode.NewTocken);
            }
        } else {
            sqlUserManager.registerUser(newUser);
            logger.info(() -> "user " + tockenPolicy.getUserID() + " has not deistvitelnay tocken");
            return new PullingResponse(commands, RegistrationCode.lol);
        }
    }

    public boolean checkUser(User user) {
        if (!sqlUserManager.isUsernameExists(user.getUsername())) {
            return false;
        }
        return sqlUserManager.checkPassword(user);
    }
}
