package Groupld.Server.Util;

import Groupld.Controler.PullingRequest;
import Groupld.Controler.PullingResponse;
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
        if (sqlUserManager.isUsernameExists(newUser.getUsername()) & request.getOperation().equals("войти")) {
            if (sqlUserManager.checkPassword(newUser)) {
                logger.info(() -> "user " + newUser.getUsername() + " authorized");
                return new PullingResponse(jwtService.generateJWTToken(request.getUsername()),"добро пожаловать" +
                        ", " + request.getUsername() +  ", вход в Lab7 прошел успешно");
            } else {
                logger.info("failed login attempt");
                return new PullingResponse(null,"не получилось войти в систему, неправильный логин или пароль");
            }
        } else if (!sqlUserManager.isUsernameExists(newUser.getUsername()) & request.getOperation().equals("войти")) {
            logger.info("this user doesn't exists, failed authorization");
            return new PullingResponse(null,"данного пользователя не существует");
        }
        else if (sqlUserManager.isUsernameExists(newUser.getUsername()) & request.getOperation().equals("регистрация")) {
            logger.info("login just exists, failed registration");
            return new PullingResponse(null,"данный логин уже занят");
        }else {
            sqlUserManager.registerUser(newUser);
            logger.info(() -> "user " + newUser.getUsername() + " registered");
            return new PullingResponse(jwtService.generateJWTToken(request.getUsername()),"добро пожаловать" +
                    ", " + request.getUsername() +  ", регистрация в Lab7 прошла успешно");
        }
    }

    /*public boolean checkUser(User user) {
        if (!sqlUserManager.isUsernameExists(user.getUsername())) {
            return false;
        }
        return sqlUserManager.checkPassword(user);
    }*/
}
