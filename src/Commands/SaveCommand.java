package Commands;

import Exceptions.WrongAmountCommandsException;
import ParceFile.FileManagerReader;
import ParceFile.FileManagerWriter;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import java.io.FileNotFoundException;

public class SaveCommand implements Command{
    private FileManagerWriter fileManagerWriter;
    private CollectionManager collectionManager;
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
    public void execute(String argument) throws FileNotFoundException {
        try{
            if(!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            fileManagerWriter.writerInFile();
        }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
