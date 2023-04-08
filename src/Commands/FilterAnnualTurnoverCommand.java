package Commands;

import Exceptions.OrganizationNotFoundException;
import Utility.CollectionManager;
import Organization.Organization;
import Utility.ConsoleManager;

import java.util.Iterator;
import java.util.Objects;

/**
 * filter_by_annual_turnover command
 */
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

    /**
     * displays all items whose annual turnover matches the given
     * @param argument
     * @exception OrganizationNotFoundException
     * @exception NumberFormatException
     */
    @Override
    public void execute(String argument) {
        Float annualTur;
        try {
            annualTur = Float.parseFloat(argument.trim().replace(",", "."));
            collectionManager.methodForFilterAnnualTurnover(annualTur);
        }catch(NumberFormatException ex){
            consoleManager.println("annualTurnover must be represented by a float number, enter the correct value");
        }catch (OrganizationNotFoundException ex){
            consoleManager.println("organization with this annualTurnover was not found");
        }
    }
}
