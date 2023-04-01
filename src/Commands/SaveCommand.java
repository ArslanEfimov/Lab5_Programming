package Commands;

import Exceptions.WrongAmountCommandsException;
import ParceFile.FileManagerReader;
import ParceFile.FileManagerWriter;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class SaveCommand implements Command{
    private FileManagerWriter fileManagerWriter;
    private ConsoleManager consoleManager;
    public SaveCommand(CollectionManager collectionManager, FileManagerReader reader){
        this.fileManagerWriter = new FileManagerWriter(collectionManager, reader);
        this.consoleManager = new ConsoleManager();
    }
    public SaveCommand(){

    }
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return "saves the collection to a file";
    }

    @Override
    public void execute(String argument){
        try{
            if(!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            try {
                fileManagerWriter.writePrettyInXml();
            } catch (IOException | XMLStreamException e) {
                throw new RuntimeException(e);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
            }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
            }
        }

}
