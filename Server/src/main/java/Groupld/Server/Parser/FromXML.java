package Groupld.Server.Parser;

import Groupld.Server.CollectionManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;


public class FromXML {
    public static CollectionManager convertFromXML(File fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (CollectionManager) unmarshaller.unmarshal(fileName);
    }

}
