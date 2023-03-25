package Commands;

import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Utility.CollectionManager;
import Organization.Organization;
import Utility.ConsoleManager;

import java.util.Iterator;

public class CountGreaterThanOfficialAddressCommand implements Command{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public CountGreaterThanOfficialAddressCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public CountGreaterThanOfficialAddressCommand(){

    }
    @Override
    public String getName() {
        return "count_greater_than_official_address";
    }

    @Override
    public String getDescription() {
        return "prints the number of elements in the collection " +
                "whose official address value is greater than the given value";
    }

    @Override
    public void execute(String argument) {
        while(true) {
            try {
                if (collectionManager.getCollectionSize() != 0) {
                    consoleManager.print("enter address: ");
                    String officialAddress = consoleManager.readString();
                    if (officialAddress.isEmpty()) throw new MustNotBeEmptyException();
                    if(officialAddress.length()>130) throw new NotInDeclaredLimitsException();
                    Iterator<Organization> iter = collectionManager.getIterator();
                    int j = 0;
                    while (iter.hasNext()) {
                        Organization i = iter.next();
                        Integer result = i.getOfficialAddress().getStreet().compareTo(officialAddress);
                        if (result > 0) {
                            j += 1;
                        }
                    }
                    consoleManager.println("Count of elements: " + j);
                } else {
                    consoleManager.println("There are no elements in the collection");
                }break;
            }catch (MustNotBeEmptyException ex){
                consoleManager.println("address cannot be empty");
            }catch(NotInDeclaredLimitsException ex){
                consoleManager.println("string length must not exceed 130");

            }
        }
    }
}
