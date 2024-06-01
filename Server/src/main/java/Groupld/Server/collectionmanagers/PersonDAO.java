package Groupld.Server.collectionmanagers;

import Groupld.Controler.CollectionObjects.Person;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PersonDAO implements GenericDAO<Person, Integer> {
    private final SessionFactory sessionFactory;
    private final Logger logger;

    public PersonDAO(SessionFactory sessionFactory,Logger logger) {

        this.sessionFactory = sessionFactory;
        this.logger =  logger;
    }

    @Override
    public void save(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        }
    }

    @Override
    public Person findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Person.class, id);
        }
    }

    @Override
    public List<Person> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Person", Person.class).list();
        }
    }

    @Override
    public void update(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(person);
            transaction.commit();
        }
    }

    @Override
    public void delete(Person person) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(person);
            transaction.commit();
        }
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
