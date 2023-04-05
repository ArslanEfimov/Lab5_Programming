package ParceFile;

import Utility.CollectionManager;
import Utility.ConsoleManager;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.file.Paths;

/**
 * class for serializing data from a file
 */
public class FileManagerWriter {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private FileManagerReader fileManagerReader;

    public FileManagerWriter(CollectionManager collectionManager, FileManagerReader fileManagerReader) {
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.fileManagerReader = fileManagerReader;
    }

    /**
     * method converts data from collection to file
     * @param collectionManager
     * @throws FileNotFoundException
     * @exception JAXBException
     */
    public void writerInFile(CollectionManager collectionManager) throws FileNotFoundException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CollectionManager.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            PrintWriter printWriter;
            if(!(Paths.get(fileManagerReader.getFileName()).toFile().exists())){
                consoleManager.println("file not found, suggest to create a new file");
                consoleManager.print("enter a name for the new file (without .xml): ");
                String newFileName = consoleManager.readString().trim()+".xml";
                printWriter = new PrintWriter(newFileName);

            }
            else {
                printWriter = new PrintWriter(fileManagerReader.getFileName());
            }
            marshaller.marshal(collectionManager,printWriter);
            printWriter.close();

        }catch (JAXBException ex){
            consoleManager.println("sudden error when trying to write to file");
        }
    }
}