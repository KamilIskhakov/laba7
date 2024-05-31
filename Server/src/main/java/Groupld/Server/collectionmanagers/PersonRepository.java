package Groupld.Server.collectionmanagers;

import Groupld.Controler.CollectionObjects.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PersonRepository {
    private final PersonDAO personDAO;
    private final UserPersonDAO userPersonDAO;

    public PersonRepository(PersonDAO personDAO, UserPersonDAO userPersonDAO) {
        this.personDAO = personDAO;
        this.userPersonDAO = userPersonDAO;
    }

    public void savePerson(Person person, User user) {
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

    public void updatePerson(Person person) {
        personDAO.update(person);
    }

    public void deletePerson(Person person) {
        personDAO.delete(person);
    }

    public List<Person> findPersonsByUser(User user) {
        List<UserPerson> userPersons = userPersonDAO.findByUser(user);
        return userPersons.stream()
                .map(UserPerson::getPerson)
                .toList();
    }

    public boolean deleteAllOwned(String username) {
        try (Session session = personDAO.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.createQuery("from User where username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
            if (user != null) {
                List<UserPerson> userPersons = userPersonDAO.findByUser(user);
                for (UserPerson userPerson : userPersons) {
                    personDAO.delete(userPerson.getPerson());
                    userPersonDAO.delete(userPerson);
                }
            }
            transaction.commit();
            return true;
        } catch (Exception e) {
            personDAO.getLogger().warn("Error during removing objects from table", e);
            return false;
        }
    }
}
