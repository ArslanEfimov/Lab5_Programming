package ParceFile;

import Utility.CollectionManager;
import Utility.ConsoleManager;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class FileManagerWriter {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private FileManagerReader fileManagerReader;
    public FileManagerWriter(CollectionManager collectionManager,FileManagerReader fileManagerReader){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.fileManagerReader = fileManagerReader;
    }
public void writerInFile() throws FileNotFoundException {
        String path = fileManagerReader.getFileName();
    try(PrintWriter writer = new PrintWriter(path)){
        writer.write("<?xml version=\"1.0\"?>");
        writer.write("\n");
        writer.write("<organizations>");
        writer.write("\n");
        int size = collectionManager.getCollectionSize();
        int count = 0;
        List<String> key = fileManagerReader.getKeys();
        while (size > 0) {
            writer.write("\t<organizations" + Integer.toString(count) + ">\n");
            writer.write("\t\t<" + key.get(0) + ">\n");
            writer.write("\t\t\t" + collectionManager.getCollectinVector().get(count).getId() + "\n");
            writer.write("\t\t</" + key.get(0) + ">\n");
            writer.write("\t\t<" + key.get(1) + ">\n");
            writer.write("\t\t\t" + collectionManager.getCollectinVector().get(count).getName() + "\n");
            writer.write("\t\t</" + key.get(1) + ">\n");
            writer.write("\t\t<" + key.get(2) + ">\n");
            writer.write("\t\t\t<" + key.get(3) + ">\n");
            writer.write("\t\t\t" + collectionManager.getCollectinVector().get(count).getCoordinates().getX() + "\n");
            writer.write("\t\t\t</" + key.get(3) + ">\n");
            writer.write("\t\t\t<" + key.get(4) + ">\n");
            writer.write("\t\t\t\t" + collectionManager.getCollectinVector().get(count).getCoordinates().getY() + "\n");
            writer.write("\t\t\t</" + key.get(4) + ">\n");
            writer.write("\t\t</" + key.get(2) + ">\n");
            writer.write("\t\t<" + key.get(5) + ">\n");
            writer.write("\t\t\t" + collectionManager.getCollectinVector().get(count).getCreationDate() + "\n");
            writer.write("\t\t</" + key.get(5) + ">\n");
            writer.write("\t\t<" + key.get(6) + ">\n");
            writer.write("\t\t\t" + collectionManager.getCollectinVector().get(count).getAnnualTurnover() + "\n");
            writer.write("\t\t</" + key.get(6) + ">\n");
            writer.write("\t\t<" + key.get(7) + ">\n");
            writer.write("\t\t\t" + collectionManager.getCollectinVector().get(count).getFullName() + "\n");
            writer.write("\t\t</" + key.get(7) + ">\n");
            writer.write("\t\t<" + key.get(8) + ">\n");
            writer.write("\t\t\t" + collectionManager.getCollectinVector().get(count).getType() + "\n");
            writer.write("\t\t</" + key.get(8) + ">\n");
            writer.write("\t\t<" + key.get(9) + ">\n");
            writer.write("\t\t\t<" + key.get(10) + ">\n");
            writer.write("\t\t\t\t" + collectionManager.getCollectinVector().get(count).getOfficialAddress().getStreet() + "\n");
            writer.write("\t\t\t</" + key.get(10) + ">\n");
            writer.write("\t\t</" + key.get(9) + ">\n");
            writer.write("\t</organizations" + Integer.toString(count) + ">\n");
            size -= 1;
            count += 1;
        }
        writer.write("\n");
        writer.write("</organizations>");
        writer.flush();
    }catch(FileNotFoundException ex){
        consoleManager.println("sdfdsf");
    }

}
}
