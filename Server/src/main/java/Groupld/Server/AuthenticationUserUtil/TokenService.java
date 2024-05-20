package Groupld.Server.AuthenticationUserUtil;

public interface TokenService {
    String generateToken(String clientId);
}
