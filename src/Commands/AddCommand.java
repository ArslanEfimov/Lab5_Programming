package Commands;

import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Exceptions.WrongAmountCommandsException;
import Exceptions.WrongValuesException;
import Organization.Organization;
import Utility.AskerOrganizations;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import java.time.LocalDate;

public class AddCommand implements Command{
    private ConsoleManager consoleManager;
    private AskerOrganizations askerOrganizations;
    private CollectionManager collectionManager;
    public AddCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.askerOrganizations = new AskerOrganizations();
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

    @Override
    public void execute(String argument) throws MustNotBeEmptyException, WrongValuesException, NotInDeclaredLimitsException {
        try {
             if (!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
             Organization organization = new Organization();
             organization.setId(collectionManager.generateId());
             organization.setName(askerOrganizations.setName());
             organization.setCoordinates(askerOrganizations.setCoordinates());
             organization.setCreationDate(LocalDate.now());
             organization.setAnnualTurnover(askerOrganizations.setAnnualTurnover());
             organization.setFullName(askerOrganizations.setFullName());
             organization.setOfficialAddress(askerOrganizations.setOfficialAddress());
             organization.setType(askerOrganizations.setType());
             collectionManager.addNewElement(organization);
             consoleManager.println("organization has been successfully added to the collection!");
        }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }


}