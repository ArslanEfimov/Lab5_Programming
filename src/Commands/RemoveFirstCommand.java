package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Organization.Organization;
import Utility.ConsoleManager;

import java.util.Iterator;

/**
 * remove_first command
 */
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

    /**
     * remove first element in collection
     * @param argument
     * @exception WrongAmountCommandsException
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            if (collectionManager.getCollectionSize() != 0) {
                collectionManager.iteratorRemoveFirstElement();
                consoleManager.println("The first element was successfully removed!");
            } else {
                consoleManager.println("There are no items in the collection!");
            }
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}