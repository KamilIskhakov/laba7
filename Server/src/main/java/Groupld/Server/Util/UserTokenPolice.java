package Groupld.Server.Util;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import org.apache.logging.log4j.Logger;

public class UserTokenPolice {
    private final Logger logger;
    private final JWTService jwtService;
    public UserTokenPolice(Logger logger, JWTService jwtService) {
        this.logger = logger;
        this.jwtService = jwtService;
    }

    public boolean verify(ReceivedData request) {
        String token = request.getRequest().getToken();
        if (jwtService.verifyJWTToken(token)) {
            request.setUsername(jwtService.decryptUserJWTToken(token));
            logger.info("user " + jwtService.decryptUserJWTToken(token) + " has correct token");
            return true;
        } else {
            logger.info(() -> "user " + " has not correct token");
            return false;
        }
    }
}
