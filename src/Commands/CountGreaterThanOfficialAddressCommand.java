package Commands;

import Collection.CollectionOfOrgs;
import Organization.Organization;

import java.util.Iterator;
import java.util.Scanner;

public class CountGreaterThanOfficialAddressCommand{
    public void countGreaterThanOfficAddress(){
        if(CollectionOfOrgs.getOrganizationVector().size()!=0) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите адрес: ");
            String officialAddress = scan.nextLine();
            Iterator<Organization> iter = CollectionOfOrgs.getOrganizationVector().iterator();
            Integer j = 0;
            while (iter.hasNext()) {
                Organization i = iter.next();
                Integer result = i.getOfficialAddress().getStreet().compareTo(officialAddress);
                if (result > 0) {
                    j += 1;
                }
            }
            System.out.println("Количество эдементов: " + j);
        }
        else{
            System.out.println("Элементов в коллекции нет");
        }

    }

}
