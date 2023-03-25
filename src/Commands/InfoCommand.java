package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

public class InfoCommand implements Command{

    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public InfoCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public InfoCommand(){

    }
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "information about collection";
    }

    @Override
    public void execute(String argument) {
        try {
            if(!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            consoleManager.println("Type of collection: Organization");
            consoleManager.println("Count elements in collection: " + collectionManager.getCollectionSize());
            if (collectionManager.getCollectionSize() != 0) {
                consoleManager.println("Inicialization date: " + collectionManager.getFirstElement().getCreationDate());
            }
        }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}