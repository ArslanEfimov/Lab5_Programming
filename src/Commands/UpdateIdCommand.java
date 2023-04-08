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
            Long id = Long.parseLong(argument.trim().replace(",", "."));
            collectionManager.methodForUpdateId(id);
        }catch(NumberFormatException ex){
            consoleManager.println("id must be represented by a number, enter the correct value");
        }

    }
}
