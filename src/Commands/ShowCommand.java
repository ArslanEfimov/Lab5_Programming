package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

/**
 * show command
 */
public class ShowCommand implements Command{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public ShowCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public ShowCommand(){

    }
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return "The command shows all items in the collection";
    }

    /**
     * The command shows all items in the collection
     * @param argument
     * @exception WrongAmountCommandsException
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            collectionManager.show();
        } catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
