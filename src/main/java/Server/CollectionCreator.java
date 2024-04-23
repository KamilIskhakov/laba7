package Server;

import Server.Parser.FromXML;
import jakarta.xml.bind.JAXBException;


import java.io.File;
import java.util.ArrayDeque;

public final class CollectionCreator {
    private CollectionCreator() {
    }

    public static CollectionManager load(String filePath){
        File file = new File(filePath);
        CollectionManager collectionManager;
        if (file.exists() && file.length() != 0) {
            try {
                collectionManager = FromXML.convertFromXML(file);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
            collectionManager.setFilePath(filePath);
        } else {
            collectionManager = new CollectionManager(new ArrayDeque<>(), filePath);
        }
        return collectionManager;
    }
}

