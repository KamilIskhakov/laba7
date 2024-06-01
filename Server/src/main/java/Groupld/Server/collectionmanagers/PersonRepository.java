package Groupld.Server.collectionmanagers;

import Groupld.Controler.CollectionObjects.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class PersonRepository {
    private final PersonDAO personDAO;
    private final UserPersonDAO userPersonDAO;

    public PersonRepository(PersonDAO personDAO, UserPersonDAO userPersonDAO) {
        this.personDAO = personDAO;
        this.userPersonDAO = userPersonDAO;
    }

    public void savePerson(Person person, User user) {
        person.setCreationDate(new Date());
        personDAO.save(person);
        UserPerson userPerson = new UserPerson();
        userPerson.setUser(user);
        userPerson.setPerson(person);
        userPersonDAO.save(userPerson);
    }

    public Person findPersonById(Integer id) {
        return personDAO.findById(id);
    }

    public List<Person> findAllPersons() {
        return personDAO.findAll();
    }

    public void updatePerson(Person person, User user) {
        List<UserPerson> userPersons = userPersonDAO.findByUser(user);
        boolean isOwnedByUser = userPersons.stream()
                .anyMatch(userPerson -> userPerson.getPerson().getId().equals(person.getId()));

        if (isOwnedByUser) {
            person.setCreationDate(new Date());
            personDAO.update(person);
        } else {
            throw new IllegalArgumentException("User does not own this person object and cannot update it.");
        }
    }

    public void deletePerson( User user) {
        // Получаем все объекты UserPerson для данного пользователя
        List<UserPerson> userPersons = userPersonDAO.findByUser(user);


        if (true) {
            // Удаляем объект Person
            userPersonDAO.delete(userPersons.get(0));

        } else {
            throw new IllegalArgumentException("User does not own this person object and cannot delete it.");
        }
    }

    public List<Person> findPersonsByUser(User user) {
        List<UserPerson> userPersons = userPersonDAO.findByUser(user);
        return userPersons.stream()
                .map(UserPerson::getPerson)
                .toList();
    }

    public boolean deleteAllOwned(String username) {
        Transaction transaction = null;
        try (Session session = personDAO.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Получаем объект User
            User user = session.createQuery("from User where username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();

            if (user != null) {
                // Получаем все связанные UserPerson
                List<UserPerson> userPersons = session.createQuery("from UserPerson where user = :user", UserPerson.class)
                        .setParameter("user", user)
                        .list();

                // Удаляем объекты
                for (UserPerson userPerson : userPersons) {
                    session.delete(userPerson);
                }
            }

            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            personDAO.getLogger().warn("Error during removing objects from table", e);
            return false;
        }
    }


    public List<Person> sortPersonsByHeight(User user) {
        List<UserPerson> userPersons = userPersonDAO.findByUser(user);
        return userPersons.stream()
                .map(UserPerson::getPerson)
                .sorted(Comparator.comparingInt(Person::getHeight)).toList();
    }
    public List<Person> sortPersonsByLocation(User user) {
        List<UserPerson> userPersons = userPersonDAO.findByUser(user);
        return userPersons.stream()
                .map(UserPerson::getPerson)
                .sorted()
                .toList();
    }
}
