package ParceFile;

import Organization.Organization;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;

/**
 * class for desserializing data from a file
 */
public class FileManagerReader {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private String fileName;


    public FileManagerReader(String fileName) {
        consoleManager = new ConsoleManager();
        collectionManager = new CollectionManager();
        this.fileName = fileName;
    }

    /**
     * a method that converts the data from the file to the desired organization data types, the JAXB parser is used
     * @return Vector<Organization>
     * @exception JAXBException
     * @exception IllegalArgumentException
     */
    public Vector<Organization> readCollection() {
        try {
            File file = new File(fileName);
            JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            collectionManager = (CollectionManager) unmarshaller.unmarshal(file);
            collectionManager.checkCollection(collectionManager.getCollectinVector());
            return collectionManager.getCollectinVector();
        }  catch (JAXBException ex) {
            consoleManager.println("the file is corrupted, please check it");
            return new Vector<>();
        } catch (IllegalArgumentException ex) {
            if (Paths.get(fileName).toFile().exists()) {
                consoleManager.println("the file cannot be read, check the permission");
            }
            else {
                consoleManager.println("file not found, check the file name");
           }
            return new Vector<>();
        }
    }

    /**
     *
     * @return fileName
     */
    public String getFileName () {
        return fileName;
    }
}