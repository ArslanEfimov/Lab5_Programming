package Commands;
import Exceptions.WrongAmountCommandsException;
import Utility.CollectionManager;
import Utility.ConsoleManager;

/**
 * clear command
 */
public class ClearCommand implements Command {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public ClearCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public ClearCommand(){

    }
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "clear collection";
    }

    /**
     * clears all elements in the collection
     * @param argument
     * @exception WrongAmountCommandsException
     */
    @Override
    public void execute(String argument) {
        try {
            if(collectionManager.getCollectionSize()!=0) {
                if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
                collectionManager.clear();
                consoleManager.println("Collection сleared");
            }else{
                consoleManager.println("There are no elements in the collection");
            }
        }catch (WrongAmountCommandsException ex) {
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }

    }
}
