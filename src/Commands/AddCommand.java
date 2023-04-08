package Commands;

import Exceptions.IncorrectValueException;
import Exceptions.WrongAmountCommandsException;
import Organization.Organization;
import Utility.AskerOrganizations;
import Utility.CollectionManager;
import Utility.ConsoleManager;


/**
 * add command
 */

public class AddCommand implements Command{
    private ConsoleManager consoleManager;
    private AskerOrganizations askerOrganizations;
    private CollectionManager collectionManager;
    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.askerOrganizations = new AskerOrganizations(collectionManager);
    }
    public AddCommand(){

    }
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "add new element to collection";
    }

    /**
     * adds a new element to the collection
     * @param argument
     * @exception WrongAmountCommandsException
     * @exception IncorrectValueException
     */
    @Override
    public void execute(String argument){
        try {
             if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
             Organization organization = new Organization();
             askerOrganizations.setOrganization(organization);
             collectionManager.addNewElement(organization);
             consoleManager.println("organization has been successfully added to the collection!");
        }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }


}