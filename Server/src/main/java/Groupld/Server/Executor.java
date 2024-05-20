package Groupld.Server;

import Groupld.Controler.ChannelClientServerUtil.ServerResponse;
import Groupld.Controler.RequestFactoryDTO.RequestDTO;
import Groupld.Server.Util.ServerRequestFromClientManager;
import Groupld.Server.Util.User;
import Groupld.Server.collectionmanagers.SQLCollectionManager;
import Groupld.Server.usersmanagers.SQLUserManager;
import org.apache.logging.log4j.Logger;

/*Метод fork() — разделяет процесс на части
Метод Join() склеивает все обратно и дожидается выполнения
Метод invokeAll() выполняет задачку, которую мы передадим и вернет результат выполнения.*/
public class Executor {
    private ServerRequestFromClientManager serverRequestFromClientManager;
    private final SQLCollectionManager sqlCollectionManager;
    private final Logger logger;

    public Executor(SQLCollectionManager sqlCollectionManager, Logger logger) {
        this.sqlCollectionManager  = sqlCollectionManager;
        this.logger = logger;
    }
    public ServerResponse executeCommand(RequestDTO request) {
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
}
