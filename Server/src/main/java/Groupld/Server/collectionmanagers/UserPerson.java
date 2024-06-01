package Groupld.Server.collectionmanagers;

import Groupld.Controler.CollectionObjects.Person;

import javax.persistence.*;


@Entity
@Table(name = "user_person")
public class UserPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Person getPerson() { return person; }
    public void setPerson(Person person) { this.person = person; }
}
