package Commands;

import Exceptions.OrganizationNotFoundException;
import Utility.CollectionManager;
import Organization.Organization;
import Utility.ConsoleManager;

import java.util.Iterator;
import java.util.Objects;

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

    @Override
    public void execute(String argument) {
        try {
            if(collectionManager.getCollectionSize()!=0) {
                Long id = Long.parseLong(argument);
                if(id>0) {
                    if (collectionManager.getById(id) == null) throw new OrganizationNotFoundException();
                    if(collectionManager.iteratorForRemoveById(id)==true){
                        consoleManager.println("Element deleted successfully");
                    }
                    else{
                        consoleManager.println("This id is not in the collection");
                    }
                }else{
                    consoleManager.println("id must be greater than 0");
                }
            }else{
                consoleManager.println("There are no elements in the collection");
            }
        } catch (NumberFormatException ex) {
            consoleManager.println("id must be represented by a number, enter the correct value");
        } catch (OrganizationNotFoundException ex) {
            consoleManager.println("organization with this id was not found");
        }
    }
}
