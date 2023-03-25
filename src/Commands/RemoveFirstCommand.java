package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Organization.Organization;
import Utility.ConsoleManager;

import java.util.Iterator;

public class RemoveFirstCommand implements Command {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public RemoveFirstCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public RemoveFirstCommand(){

    }
    @Override
    public String getName() {
        return "remove_first";
    }

    @Override
    public String getDescription() {
        return "remove first element in collection";
    }

    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            Iterator<Organization> iter = collectionManager.getIterator();
            if (collectionManager.getCollectionSize() != 0) {
                while (iter.hasNext()) {
                    iter.next();
                    iter.remove();
                    consoleManager.println("The first element was successfully removed!");
                    break;
                }
            } else {
                consoleManager.println("There are no items in the collection!");
            }
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}