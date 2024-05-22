package Groupld.Server.collectionmanagers;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Server.collectionmanagers.datamanagers.SQLDataManager;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SQLCollectionManager extends CollectionManager {
    private final ConcurrentHashMap<Integer, Person> collection;
    private final SQLDataManager sqlDataManager;

    public SQLCollectionManager(ConcurrentHashMap<Integer, Person> collection, SQLDataManager sqlDataManager) {
        super(collection);
        this.collection = collection;
        this.sqlDataManager = sqlDataManager;
    }

    @Override
    public void addToCollection(Person person) {
        person.setCreationDate(new Date());
        Integer id = sqlDataManager.add(person);
        person.setId(id);
        collection.put(id,person);
    }


    @Override
    public boolean updateID(Integer id, Person newInstance) {
        Person oldInstance = getById(id);
        if (!oldInstance.getOwnerUsername().equals(newInstance.getOwnerUsername())) { // надо дописать
            return false;
        }
        if (!sqlDataManager.update(id, newInstance)) {
            return false;
        }
        oldInstance.setName(newInstance.getName());
        oldInstance.setCoordinates(newInstance.getCoordinates());
        oldInstance.setHeight(newInstance.getHeight());
        oldInstance.setWeight(newInstance.getWeight());
        oldInstance.setEyeColor(newInstance.getEyeColor());
        oldInstance.setNationality(newInstance.getNationality());
        oldInstance.setLocation(newInstance.getLocation());
        return true;
    }

    @Override
    public boolean remove(Integer key) {
        if (!sqlDataManager.removeById(key)) {
            return false;
        }
        collection.remove(key);
        return true;
    }

    @Override
    public boolean clear(String username) {
        if (!sqlDataManager.deleteAllOwned(username)) {
            return false;
        }
        collection.entrySet().removeIf(e -> e.getValue().getOwnerUsername().equals(username));
        return true;
    }

    @Override
    public Integer removeGreater(Person person, String username) {
        AtomicInteger undeletedItems = new AtomicInteger();
        List<Integer> keys = collection.entrySet().stream().filter(e -> e.getValue().compareTo(person) > 0
                && e.getValue().getOwnerUsername().equals(username)).map(Map.Entry::getKey).collect(Collectors.toList());
        keys.forEach(k -> {
            if (sqlDataManager.removeById(k)) {
                collection.remove(k);
            } else {
                undeletedItems.getAndIncrement();
            }
        });
        return undeletedItems.get();
    }


    @Override
    public Integer removeLowerKey(Integer key, String username) {
        AtomicInteger undeletedItems = new AtomicInteger();
        List<Integer> keys = collection.entrySet().stream().filter(e -> e.getKey() < key
                && e.getValue().getOwnerUsername().equals(username)).map(Map.Entry::getKey).collect(Collectors.toList());
        keys.forEach(k -> {
            if (sqlDataManager.removeById(k)) {
                collection.remove(k);
            } else {
                undeletedItems.getAndIncrement();
            }
        });
        return undeletedItems.get();
    }

}
