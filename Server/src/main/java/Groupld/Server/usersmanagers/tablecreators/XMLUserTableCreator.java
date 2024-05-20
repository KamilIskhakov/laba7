package Groupld.Server.usersmanagers.tablecreators;

import com.bhma.server.usersmanagers.XMLUserManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public final class XMLUserTableCreator {
    private XMLUserTableCreator() {
    }

    public static void convertToXML(XMLUserManager xmlUserManager, String fileName) throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(XMLUserManager.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
        marshaller.marshal(xmlUserManager, bufferedOutputStream);
        bufferedOutputStream.close();
    }

    private static XMLUserManager convertToJavaObject(File fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(XMLUserManager.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (XMLUserManager) unmarshaller.unmarshal(fileName);
    }

    public static XMLUserManager getCollectionManagerFromXML(String filePath, Logger logger) throws JAXBException {
        File file = new File(filePath);
        XMLUserManager xmlUserManager;
        if (file.exists() && file.length() != 0) {
            xmlUserManager = convertToJavaObject(file);
            xmlUserManager.setFilename(filePath);
        } else {
            xmlUserManager = new XMLUserManager(new ReentrantLock(), new ArrayList<>(), filePath);
        }
        if (file.exists()) {
            logger.info(() -> "users was successfully loaded from the file " + filePath);
        } else {
            logger.info("No file with this name was found. A new empty user's collection has been created");
        }
        return xmlUserManager;
    }
}
