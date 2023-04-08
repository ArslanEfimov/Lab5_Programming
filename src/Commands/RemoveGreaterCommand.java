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
            Float annualTurn = Float.parseFloat(argument.trim().replace(",","."));
            collectionManager.methodForRemoveGreater(annualTurn);
        }catch(NumberFormatException ex) {
            consoleManager.println("annualTurnover must be represented by a float number, enter the correct value");
        }
    }
}