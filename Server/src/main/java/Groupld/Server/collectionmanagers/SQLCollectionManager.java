package Groupld.Server.collectionmanagers;

import Groupld.Controler.CollectionObjects.Person;
import Groupld.Server.collectionmanagers.datamanagers.SQLDataManager;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
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
    public void addToCollection(Long key, SpaceMarine spaceMarine) {
        spaceMarine.setCreationDate(new Date());
        Long id = sqlDataManager.add(key, spaceMarine);
        if (id != null) {
            spaceMarine.setId(id);
            collection.put(key, spaceMarine);
        }
    }

    @Override
    public boolean updateID(long id, SpaceMarine newInstance) {
        SpaceMarine oldInstance = getById(id);
        if (!oldInstance.getOwnerUsername().equals(newInstance.getOwnerUsername())) {
            return false;
        }
        if (!sqlDataManager.update(id, newInstance)) {
            return false;
        }
        oldInstance.setName(newInstance.getName());
        oldInstance.setCoordinates(newInstance.getCoordinates());
        oldInstance.setHealth(newInstance.getHealth());
        oldInstance.setCategory(newInstance.getCategory());
        oldInstance.setWeaponType(newInstance.getWeaponType());
        oldInstance.setMeleeWeapon(newInstance.getMeleeWeapon());
        oldInstance.setChapter(newInstance.getChapter());
        return true;
    }

    @Override
    public boolean remove(long key) {
        if (!sqlDataManager.removeByKey(key)) {
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
    public long removeGreater(SpaceMarine spaceMarine, String username) {
        AtomicLong undeletedItems = new AtomicLong();
        List<Long> keys = collection.entrySet().stream().filter(e -> e.getValue().compareTo(spaceMarine) > 0
                && e.getValue().getOwnerUsername().equals(username)).map(Map.Entry::getKey).collect(Collectors.toList());
        keys.forEach(k -> {
            if (sqlDataManager.removeByKey(k)) {
                collection.remove(k);
            } else {
                undeletedItems.getAndIncrement();
            }
        });
        return undeletedItems.get();
    }

    @Override
    public long removeLowerKey(long key, String username) {
        AtomicLong undeletedItems = new AtomicLong();
        List<Long> keys = collection.entrySet().stream().filter(e -> e.getKey() < key
                && e.getValue().getOwnerUsername().equals(username)).map(Map.Entry::getKey).collect(Collectors.toList());
        keys.forEach(k -> {
            if (sqlDataManager.removeByKey(k)) {
                collection.remove(k);
            } else {
                undeletedItems.getAndIncrement();
            }
        });
        return undeletedItems.get();
    }

    @Override
    public boolean removeAnyByWeaponType(Weapon weapon, String username) {
        Optional<Map.Entry<Long, SpaceMarine>> item = collection.entrySet().stream().filter(e -> e.getValue().
                getWeaponType().equals(weapon) && e.getValue().getOwnerUsername().equals(username)).findFirst();
        if (!item.isPresent()) {
            return false;
        }
        if (!sqlDataManager.removeByKey(item.get().getKey())) {
            return false;
        }
        collection.remove(item.get().getKey());
        return true;
    }
}
