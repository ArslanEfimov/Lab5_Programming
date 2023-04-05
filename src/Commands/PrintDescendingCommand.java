package Commands;

import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

/**
 * print_descending command
 */
public class PrintDescendingCommand implements Command{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public PrintDescendingCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public PrintDescendingCommand(){

    }
    @Override
    public String getName() {
        return "print_descending";
    }

    @Override
    public String getDescription() {
        return "command lists all elements in descending order";
    }

    /**
     * command lists all elements in descending order
     * @param argument
     * @exception WrongAmountCommandsException
     */
    @Override
    public void execute(String argument) {
        try {
            if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            consoleManager.println(collectionManager.printDescending());
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }
}
