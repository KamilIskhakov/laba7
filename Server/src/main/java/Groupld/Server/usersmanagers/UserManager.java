package Groupld.Server.usersmanagers;


import Groupld.Server.Util.User;

import java.util.List;
import java.util.concurrent.locks.Lock;

public abstract class UserManager {
    private final List<User> users;
    private final Lock lock;

    public UserManager(List<User> users, Lock lock) {
        this.users = users;
        this.lock = lock;
    }

    public boolean isUsernameExists(String username) {
        try {
            lock.lock();
            return users.stream().anyMatch(e -> e.getUsername().equals(username));
        } finally {
            lock.unlock();
        }
    }

    public boolean checkPassword(User user) {
        try {
            lock.lock();
            return users.stream().anyMatch(e -> e.equals(user));
        } finally {
            lock.unlock();
        }
    }

    public abstract void registerUser(User user);
}
