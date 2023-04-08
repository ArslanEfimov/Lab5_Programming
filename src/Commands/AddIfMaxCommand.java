package Commands;


import Exceptions.*;
import Organization.Organization;
import Utility.AskerOrganizations;
import Utility.CollectionManager;
import Utility.ConsoleManager;


/**
 * add if max command
 */
public class AddIfMaxCommand implements Command{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private AskerOrganizations askerOrganizations;
    public AddIfMaxCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.askerOrganizations = new AskerOrganizations(collectionManager);
    }
    public AddIfMaxCommand(){

    }
    @Override
    public String getName() {
        return "add_if_max";
    }

    @Override
    public String getDescription() {
        return "adds an element to the collection if it exceeds the value of the largest element";
    }

    /**
     * adds a new element to the collection if its annual turnover is greater than the maximum in the collection
     * @param argument
     * @exception WrongAmountCommandsException
     * @exception IncorrectValueException
     */
    @Override
    public void execute(String argument){
        try {
            if(!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            Organization organization = new Organization();
            askerOrganizations.setOrganization(organization);
            collectionManager.methodForAddIfMax(organization);
        }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }

}


