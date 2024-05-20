package Groupld.Server.collectionmanagers;

import Groupld.Controler.CollectionObjects.Person;

import java.util.Date;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;


public abstract class CollectionManager {
    private final ConcurrentHashMap<Integer, Person> collection;
    private final Date dateOfInitialization = new Date();

    public CollectionManager(ConcurrentHashMap<Integer, Person> collection) {
        this.collection = collection;
    }

    public ConcurrentHashMap<Integer, Person> getCollection() {
        return collection;
    }

  
    public String collectionInfo() {
        return "Collection type: " + collection.getClass().getName() + "\n"
                + "Date of initialization: " + dateOfInitialization + "\n"
                + "Collection size: " + collection.size();
    }

    public abstract void addToCollection(Integer key, Person person);

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        if (collection.size() > 0) {
            collection.forEach((k, v) -> stringJoiner.add(k + ": " + v.toString()));
        } else {
            stringJoiner.add("The collection is empty");
        }
        return stringJoiner.toString();
    }

    public Person getById(Integer id) {
        return collection.values().stream().filter(v -> v.getId().equals(id)).findFirst().get();
    }

    public abstract boolean updateID(Integer id, Person newInstance);

    public boolean containsId(Integer id) {
        return collection.values().stream().anyMatch(v -> v.getId().equals(id));
    }

    public boolean containsKey(Integer key) {
        return collection.containsKey(key);
    }

    public Person getByKey(Integer key) {
        return collection.get(key);
    }

    public abstract boolean remove(Integer key);

    public abstract boolean clear(String username);

    public abstract Integer removeGreater(Person person, String username);

    public abstract Integer removeLowerKey(Integer key, String username);

}
