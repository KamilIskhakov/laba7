package Server;

import Client.Main;
import CollectionObjects.Location;
import CollectionObjects.Person;
import jakarta.xml.bind.annotation.*;

import java.util.*;

@XmlRootElement(name = "personList")
@XmlAccessorType(XmlAccessType.FIELD)
public class CollectionManager {
    @XmlElement(name = "person")
    private ArrayDeque<Person> personcollection = new ArrayDeque<>();
    private final Date dateOfInitialization = new Date();
    private String filePath;

    public CollectionManager(ArrayDeque<Person> personcollection, String filePath) {
        this.personcollection = personcollection;
        this.filePath = filePath;
    }

    public CollectionManager() {
    }

    private ArrayDeque<Person> getCollection() {
        return personcollection;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private void setPersonCollection(ArrayDeque<Person> people) {
        personcollection = people;
    }

    public void addToCollection(Person person) {
        person.setId(generateId());
        person.setCreationDate(new Date());
        personcollection.addLast(person);
    }

    public void removeCollectionById(Integer id) {
        for (Person person : personcollection)
            if (Objects.equals(person.getId(), id))
                personcollection.remove(person);
    }

    public void removeHead() {
        personcollection.poll();
    }

    public String showHead() {
        return personcollection.getFirst().toString();
    }

    public void save() {
        ToXML.convertToXML(this, filePath);
    }

    public void clear() {
        personcollection.clear();
    }

    public int[] GroupPeople() {
        Object[] arrayObjectPeople = personcollection.toArray();
        Person[] arrayPeople = new Person[arrayObjectPeople.length];
        int[] countName = new int[1024];
        for (int i = 0; i < arrayPeople.length; i++) {
            arrayPeople[i] = (Person) arrayObjectPeople[i];
            int j = arrayPeople[i].getName().length();
            countName[j] += 1;
        }
        return countName;
    }

    public void FilterGreaterThanHeight(Integer height) {
        Object[] arrayObjectPeople = personcollection.toArray();
        Person[] arrayPeople = new Person[arrayObjectPeople.length];
        for (int i = 0; i < arrayPeople.length; i++) {
            arrayPeople[i] = (Person) arrayObjectPeople[i];
            if (arrayPeople[i].getHeight() > height) {
                Main.terminalOutputManager.print(arrayPeople[i].getName() + " ");
            }
        }
    }

    public void FilterLessThanLocation(Location location) {
        Object[] arrayObjectPeople = personcollection.toArray();
        Person[] arrayPeople = new Person[arrayObjectPeople.length];
        for (int i = 0; i < arrayPeople.length; i++) {
            arrayPeople[i] = (Person) arrayObjectPeople[i];
            if (arrayPeople[i].compareTo(location) < 0) {
                Main.terminalOutputManager.print(arrayPeople[i].getName() + " ");
            }
        }
    }

    public void showCollection() {
        for (Person person : personcollection) {
            Main.terminalOutputManager.print(person.getName() + " ");
        }
        Main.terminalOutputManager.println(" ");
    }

    public void update(Person userPerson, Integer id) {
        ArrayDeque<Person> people = new ArrayDeque<>();
        Object[] arrayObjectPeople = personcollection.toArray();
        Person[] arrayPeople = new Person[arrayObjectPeople.length];
        for (int i = 0; i < arrayPeople.length; i++) {
            arrayPeople[i] = (Person) arrayObjectPeople[i];
            arrayPeople[i].setCreationDate(new Date());
            arrayPeople[i].setId(generateId());
            if (Objects.equals(arrayPeople[i].getId(), id)) {
                arrayPeople[i] = userPerson;
            }
            people.add(arrayPeople[i]);
        }
        setPersonCollection(people);
    }

    public Integer generateId() {
        int count = 0;
        int id = 1;
        while (count != personcollection.size()) {
            for (Person person : personcollection) {
                count++;
                if (person.getId() == id) {
                    id++;
                    count = 0;
                    break;
                }
            }
        }
        return id;
    }

    public String collectionInfo() {
        return "Collection type: " + personcollection.getClass().getName() + "\n"
                + "Date of initialization: " + dateOfInitialization + "\n"
                + "Collection size: " + personcollection.size();
    }


}
