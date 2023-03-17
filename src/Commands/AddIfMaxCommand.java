package Commands;

import Collection.CollectionOfOrgs;
import Exceptions.WrongValuesException;
import Organization.Organization;

import java.util.Iterator;

public class AddIfMaxCommand{
    public void addIfMax() throws WrongValuesException {
        AddCommand addCommand = new AddCommand();
        Organization organization = addCommand.elementAdding();
        Iterator<Organization> iter = CollectionOfOrgs.getOrganizationVector().iterator();
        int count = 0;
        while(iter.hasNext()){
            Organization i = iter.next();
            Integer result = organization.getAnnualTurnover().compareTo(i.getAnnualTurnover());
            if(result > 0){
                count+=1;
            }
        }
        if(count == CollectionOfOrgs.getOrganizationVector().size()){
            CollectionOfOrgs.getOrganizationVector().add(organization);
            System.out.println("Элемент был успешно добавлен");
        }
        else{
            System.out.println("Элемент не был добавлен, так как его значение меньше наибольшего элемента");
        }

    }
}
