package Groupld.Controler;

import java.io.Serializable;

public class PullingRequest implements Serializable {
    private final String username;
    private final String password;

    public PullingRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        String commandName = "pull commands";
        return "PullingRequest{"
                + " commandName='" + commandName + '\''
                + '}';
    }
}
