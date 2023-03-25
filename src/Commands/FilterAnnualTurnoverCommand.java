package Commands;

import Exceptions.OrganizationNotFoundException;
import Utility.CollectionManager;
import Organization.Organization;
import Utility.ConsoleManager;

import java.util.Iterator;
import java.util.Objects;

public class FilterAnnualTurnoverCommand implements Command{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    public FilterAnnualTurnoverCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
    }
    public FilterAnnualTurnoverCommand(){

    }
    @Override
    public String getName() {
        return "filter_by_annual_turnover";
    }

    @Override
    public String getDescription() {
        return "displays all items whose annual turnover matches the given";
    }

    @Override
    public void execute(String argument) {
        Float annualTur;
        try {
            Iterator<Organization> iter = collectionManager.getIterator();
            if (collectionManager.getCollectionSize() != 0) {
                annualTur = Float.parseFloat(argument);
                if(collectionManager.getByAnnualTurnover(annualTur)==null) throw new OrganizationNotFoundException();
                while (iter.hasNext()) {
                    Organization i = iter.next();
                    if (Objects.equals(i.getAnnualTurnover(), annualTur)) {
                        consoleManager.println(i);
                    }
                }
            } else {
                consoleManager.println("There are no items in the collection");
            }
        }catch(NumberFormatException ex){
            consoleManager.println("annualTurnover must be represented by a float number, enter the correct value");
        }catch (OrganizationNotFoundException ex){
            consoleManager.println("organization with this annualTurnover was not found");
        }
    }
}
