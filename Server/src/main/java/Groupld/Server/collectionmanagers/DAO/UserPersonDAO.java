package Groupld.Server.collectionmanagers.DAO;

import Groupld.Server.Server;
import Groupld.Server.collectionmanagers.User;
import Groupld.Server.collectionmanagers.UserPerson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public class UserPersonDAO {
    private final SessionFactory sessionFactory;

    public UserPersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(UserPerson userPerson) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Server.LOGGER.info("Starting transaction for saving UserPerson");
            long startTime = System.currentTimeMillis();
            transaction = session.beginTransaction();

            // Перед сохранением объекта UserPerson убедитесь, что связанные объекты User и Person уже сохранены в базе данных
            session.save(userPerson.getUser());
            session.save(userPerson);

            Server.LOGGER.info("Transaction committed successfully");
            transaction.commit();
            long endTime = System.currentTimeMillis();
            Server.LOGGER.info("Transaction committed successfully in " + (endTime - startTime) + " ms");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Server.LOGGER.error("Transaction failed, rolled back", e);
            throw e;
        }
    }
    public synchronized List<UserPerson> findByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from UserPerson where user.username = :user", UserPerson.class)
                    .setParameter("user", user.getUsername())
                    .list();
        }
    }

    public synchronized void delete(UserPerson userPerson) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Server.LOGGER.info("Starting transaction for deleting UserPerson with ID: " + userPerson.getId());
            transaction = session.beginTransaction();
            session.delete(userPerson);
            transaction.commit();
            Server.LOGGER.info("Transaction committed successfully");
        } catch (ConstraintViolationException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Server.LOGGER.error("ConstraintViolationException: Could not execute statement", e);
            throw e; // Or handle it appropriately
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Server.LOGGER.error("Transaction failed, rolled back", e);
            throw e;
        }
    }
}