package Commands;

import Exceptions.OrganizationNotFoundException;
import Organization.Organization;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import java.util.Iterator;
import java.util.Objects;

/**
 * update_by_id command
 */
public class UpdateIdCommand implements Command {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public UpdateIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public UpdateIdCommand(){

    }
    @Override
    public String getName() {
        return "update_by_id";
    }

    @Override
    public String getDescription() {
        return "updates the element's id if it is equal to the given one";
    }

    /**
     * updates the element's id if it is equal to the given one
     * @param argument
     * @exception NumberFormatException
     * @exception OrganizationNotFoundException
     */
    @Override
    public void execute(String argument){
        try {
            if (collectionManager.getCollectionSize() != 0) {
                Long id = Long.parseLong(argument);
                if(id>0) {
                    if (collectionManager.getById(id) == null) throw new OrganizationNotFoundException();
                    collectionManager.iteratorForUpdateId(id);
                    consoleManager.println("element id updated successfully");
                }else{
                    consoleManager.println("id must be greater than 0");
                }
            } else {
                consoleManager.println("There are no items in the collection");
            }
        }catch(NumberFormatException ex){
            consoleManager.println("id must be represented by a number, enter the correct value");
        }catch(OrganizationNotFoundException ex){
            consoleManager.println("organization with this id was not found");
        }

    }
}
