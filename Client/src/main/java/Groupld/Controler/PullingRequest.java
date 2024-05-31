package Groupld.Controler;

import Groupld.Controler.RequestFactoryDTO.RequestDTO;

import java.io.Serializable;

public class PullingRequest implements Serializable, RequestDTO{
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


    public String getToken() {
        return null;
    }


    public void setToken(String token) {
    }


    public String getUserName() {
        return null;
    }


    public void setUserName(String userName) {

    }
}
