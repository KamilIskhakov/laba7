package Groupld.Server.Util;

import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Controler.PullingRequest;
import Groupld.Controler.PullingResponse;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Controler.Response;
import Groupld.Server.PasswordEncoder;
import Groupld.Server.Server;
import Groupld.Server.usersmanagers.SQLUserManager;
import org.apache.logging.log4j.Logger;

public class UsersHandler {
    private final SQLUserManager sqlUserManager;
    private final Logger logger;
    private final JWTService jwtService;

    public UsersHandler(SQLUserManager sqlUserManager, Logger logger, JWTService jwtService) {
        this.sqlUserManager = sqlUserManager;
        this.logger = logger;
        this.jwtService = jwtService;
    }
    public PullingResponse authorization(PullingRequest request) {
        User newUser = new User(request.getUsername(), PasswordEncoder.encode(request.getPassword()));
        if (sqlUserManager.isUsernameExists(newUser.getUsername())) {
            if (sqlUserManager.checkPassword(newUser)) {
                logger.info(() -> "user " + newUser.getUsername() + " authorized");
                return new PullingResponse(jwtService.generateJWTToken(request.getUsername()),"добро пожаловать" +
                        ", " + request.getUsername() +  ", вход в Lab7 прошел успешно");
            } else {
                logger.info("failed login attempt");
                return new PullingResponse(null,"не получилось войти в систему, неправильный логин или пароль");
            }
        } else {
            sqlUserManager.registerUser(newUser);
            logger.info(() -> "user " + newUser.getUsername() + " registered");
            return new PullingResponse(jwtService.generateJWTToken(request.getUsername()),"добро пожаловать" +
                    ", " + request.getUsername() +  ", регистрация в Lab7 прошла успешно");
        }
    }

    public ServerResponse handle(RequestDTO request) {
        String token = request.getToken();
        if (jwtService.verifyJWTToken(token)) {
            logger.info("user " + jwtService.decryptUserJWTToken(token) + " has correct token");
            return Server.serverRequestFromClientManager.getServerResponse(request);

        } else {
            logger.info(() -> "user " + jwtService.decryptUserJWTToken(token) + " has not correct token");
            return new ServerResponse("token_finished","похоже, что время вашей сессии закончилось",null);
        }
    }

    /*public boolean checkUser(User user) {
        if (!sqlUserManager.isUsernameExists(user.getUsername())) {
            return false;
        }
        return sqlUserManager.checkPassword(user);
    }*/
}
