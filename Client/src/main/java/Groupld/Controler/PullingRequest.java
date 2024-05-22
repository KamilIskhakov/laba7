package Groupld.Controler;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;

import java.io.Serializable;

public class PullingRequest implements Serializable, RequestDTO {
    private final String username;
    private final String password;

    private final String operation;

    public PullingRequest(String username, String password, String operation) {
        this.username = username;
        this.password = password;
        this.operation = operation;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        String commandName = "pull commands";
        return "PullingRequest{"
                + " commandName='" + commandName + '\''
                + '}';
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void setToken(String token) {
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void setUserName(String userName) {

    }
}
