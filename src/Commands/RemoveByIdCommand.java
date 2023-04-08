package Commands;

import Exceptions.OrganizationNotFoundException;
import Utility.CollectionManager;
import Organization.Organization;
import Utility.ConsoleManager;

import java.util.Iterator;
import java.util.Objects;

/**
 * remove_by_id command
 */
public class RemoveByIdCommand implements Command {

    //todo сюда передаем экземпляр класса ConsoleManager
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public RemoveByIdCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public RemoveByIdCommand(){

    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "removes element from collection by id ";
    }

    /**
     * removes element from collection by id
     * @param argument
     * @exception OrganizationNotFoundException
     * @exception NumberFormatException
     */
    @Override
    public void execute(String argument) {
        try {
            Long id = Long.parseLong(argument.trim());
            collectionManager.methodForRemoveById(id);
        } catch (NumberFormatException ex) {
            consoleManager.println("id must be represented by a number, enter the correct value");
        } catch (OrganizationNotFoundException ex) {
            consoleManager.println("organization with this id was not found");
        }
    }
}
