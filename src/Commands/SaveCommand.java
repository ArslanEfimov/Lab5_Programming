package Commands;

import Exceptions.WrongAmountCommandsException;
import ParceFile.FileManagerReader;
import ParceFile.FileManagerWriter;
import Utility.CollectionManager;
import Utility.ConsoleManager;
import java.io.IOException;

public class SaveCommand implements Command{
    private FileManagerWriter fileManagerWriter;
    private ConsoleManager consoleManager;
    private CollectionManager collectionManager;
    public SaveCommand(CollectionManager collectionManager, FileManagerReader reader){
        this.collectionManager = collectionManager;
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
                fileManagerWriter.writerInFile(collectionManager);
                consoleManager.println("collection is saved");
            } catch (IOException e) {
                consoleManager.println("unable to write data to the file, most likely you do not have write access to the file");
            }
            }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
            }
        }

}
