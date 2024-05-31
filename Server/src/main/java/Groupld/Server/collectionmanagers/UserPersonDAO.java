package Groupld.Server.collectionmanagers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserPersonDAO {
    private final SessionFactory sessionFactory;

    public UserPersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(UserPerson userPerson) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(userPerson);
            transaction.commit();
        }
    }

    public List<UserPerson> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from UserPerson where user = :user", UserPerson.class)
                    .setParameter("user", user)
                    .list();
        }
    }

    public void delete(UserPerson userPerson) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(userPerson);
            transaction.commit();
        }
    }
}