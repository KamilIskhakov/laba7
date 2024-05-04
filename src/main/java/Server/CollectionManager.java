package Server;

import Controler.CollectionObjects.Location;
import Controler.CollectionObjects.Person;
import Server.Parser.ToXML;
import jakarta.xml.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        personcollection.removeIf(person -> Objects.equals(person.getId(), id));
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
        IntStream countNameStream = Arrays.stream(arrayObjectPeople).mapToInt(obj -> {
            if (obj instanceof Person person) {
                return person.getName().length();
            } else {
                throw new IllegalArgumentException("Object is not a Person");
            }});
        // Создаем массив для подсчета длины имен
        int[] countName = new int[20];
        // Применяем функцию к потоку для подсчета количества каждого имени
        countNameStream.forEach(n -> countName[n] += 1 );
        return countName;
    }

    public String FilterGreaterThanHeight(Integer height) {
        final String[] result = {""};
        // Используем stream для фильтрации и преобразования списка людей
        personcollection.stream()
                .filter(person -> (person.getHeight() > height)) // Фильтрация по высоте
                .map(Person::getName) // Преобразование Person в String (имя)
                .forEach(name -> result[0] += name + "\n "); // Добавление имени в результат
        return result[0];
    }

    public String FilterLessThanLocation(Location location) {
        final String[] result = {""};
        // Используем stream для фильтрации и преобразования списка людей
        personcollection.stream()
                .filter(person -> (person.compareTo(location) < 0)) // Фильтрация по сравнению с Location
                .map(Person::getName) // Преобразование Person в String (имя)
                .forEach(name -> result[0] += name + "\n "); // Добавление имени в результат
        return result[0];
    }

    public String showCollection() {
        return personcollection.stream()
                .map(Person::getName)
                .collect(Collectors.joining(" "));
    }

    public void update(Person userPerson, Integer id) {
        personcollection.stream().map(person -> {
            if (Objects.equals(person.getId(), id)) {
                userPerson.setCreationDate(new Date());
                userPerson.setId(id); // Устанавливаем ID обратно, так как он был заменен на generateId()
                return userPerson; // Возвращаем обновленный объект пользователя
            } else {
                return person; // Возвращаем тот же объект, если ID не совпадает
            }
        });

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