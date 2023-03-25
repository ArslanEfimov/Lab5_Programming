package Commands;


import Exceptions.*;
import Organization.Organization;
import Utility.AskerOrganizations;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import java.util.Iterator;

public class AddIfMaxCommand implements Command{
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private AskerOrganizations askerOrganizations;
    public AddIfMaxCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.askerOrganizations = new AskerOrganizations();
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

    @Override
    public void execute(String argument) throws MustNotBeEmptyException, WrongValuesException, NotInDeclaredLimitsException, OrganizationNotFoundException {
        try {
            if(!argument.isEmpty() && !argument.equals(getName())) throw new WrongAmountCommandsException();
            Organization organization = new Organization();
            organization.setId(collectionManager.generateId());
            organization.setName(askerOrganizations.setName());
            organization.setCoordinates(askerOrganizations.setCoordinates());
            organization.setAnnualTurnover(askerOrganizations.setAnnualTurnover());
            organization.setFullName(askerOrganizations.setFullName());
            organization.setOfficialAddress(askerOrganizations.setOfficialAddress());
            organization.setType(askerOrganizations.setType());
            Iterator<Organization> iter = collectionManager.getIterator();
            int count = 0;
            while(iter.hasNext()){
                Organization org = iter.next();
                int result = organization.getAnnualTurnover().compareTo(org.getAnnualTurnover());
                if(result > 0){
                    count+=1;
                }
            }
            if(count == collectionManager.getCollectionSize()){
                collectionManager.addNewElement(organization);
                consoleManager.println("organization has been successfully added to the collection!");
            }
            else{
                consoleManager.println("The element was not added because its value is less than the largest element");
            }
        }catch (WrongAmountCommandsException ex){
            consoleManager.println("incorrect command usage, usage example: " + getName());
        }
    }

}


