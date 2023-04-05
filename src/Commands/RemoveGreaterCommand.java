package Commands;
import Utility.CollectionManager;
import Utility.ConsoleManager;

/**
 * remove_greater command
 */
public class RemoveGreaterCommand implements Command{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public RemoveGreaterCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public RemoveGreaterCommand(){

    }
    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return "removes elements from the collection that " +
                "are larger than the given(are compared by the value of the annualTurnover) ";
    }

    /**
     * removes elements from the collection that are larger than the given(are compared by the value of the annualTurnover)
     * @param argument
     * @exception NumberFormatException
     */
    @Override
    public void execute(String argument) {
        try {
            if (collectionManager.getCollectionSize() > 0) {
                Float annualTurn = Float.parseFloat(argument);
                int size = collectionManager.getCollectionSize();
                collectionManager.iteratorRemoveGreater(annualTurn);
                if (collectionManager.getCollectionSize() < size) {
                    consoleManager.println("Element(s) successfully removed(s)");
                } else {
                    consoleManager.println("All elements do not exceed the specified");
                }
            } else {
                consoleManager.println("There are no elements in the collection");
            }
        }catch(NumberFormatException ex) {
            consoleManager.println("annualTurnover must be represented by a float number, enter the correct value");
        }
    }
}