package Groupld.Server.usersmanagers;

import com.bhma.server.util.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.concurrent.locks.Lock;

@XmlRootElement(name = "users")
public class XMLUserManager extends UserManager {
    private final Lock lock;
    @XmlElement(name = "user")
    private List<User> users;
    private String filename;

    public XMLUserManager(Lock lock, List<User> users, String filename) {
        super(users, lock);
        this.lock = lock;
        this.users = users;
        this.filename = filename;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public void registerUser(User user) {
        try {
            lock.lock();
            users.add(user);
        } finally {
            lock.unlock();
        }
    }
}
