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

    /**
     * @param id id of a space-marine instance
     * @return space-marine instance whose id is equal to the entered one
     */
    public Person getById(Integer id) {
        return collection.values().stream().filter(v -> v.getId().equals(id)).findFirst().get();
    }

    public abstract boolean updateID(Integer id, Person newInstance);

    /**
     * @param id checking id
     * @return true if the collection has an element with that id, and false otherwise
     */
    public boolean containsId(Integer id) {
        return collection.values().stream().anyMatch(v -> v.getId().equals(id));
    }

    /**
     * @param key checking key
     * @return true if the collection has a key which is equal to the checking one, and false otherwise
     */
    public boolean containsKey(Integer key) {
        return collection.containsKey(key);
    }

    /**
     * @param key
     * @return an element in collection which is the value for the entered key
     */
    public Person getByKey(Integer key) {
        return collection.get(key);
    }

    /**
     * @param key key to the element that will be removed from the collection
     */
    public abstract boolean remove(Integer key);

    /**
     * removes all elements from the collection
     */
    public abstract boolean clear(String username);

    /**
     * removes all elements which is greater than param from the collection
     * @param person
     */
    public abstract long removeGreater(Person person, String username);

    /**
     * removes all elements which have key that lower than param
     * @param key
     */
    public abstract long removeLowerKey(Integer key, String username);

}
