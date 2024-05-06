package Server.Parser;

import Server.CollectionManager;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.File;

public class ToXML {
    public static void convertToXML(CollectionManager collectionManager, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.marshal(collectionManager, new File(fileName));
        } catch (JAXBException e) {
            System.out.println(e);
        }
    }
}
