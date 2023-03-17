package Commands;
import Collection.CollectionOfOrgs;
import Organization.Organization;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class FilterAnnualTurnoverCommand {
    public void filterAnnualTurnover(){
        Iterator<Organization> iter = CollectionOfOrgs.getOrganizationVector().iterator();
        if(CollectionOfOrgs.getOrganizationVector().size()!=0){
            System.out.print("Введите значение annualTurnover: ");
            Scanner scan = new Scanner(System.in);
            Float annualTur = scan.nextFloat();
            while(iter.hasNext()){
                Organization i = iter.next();
                if(Objects.equals(i.getAnnualTurnover(), annualTur)){
                    System.out.println(i);
                }
            }
        }
        else{
            System.out.println("Элементов в коллекции нет");
        }

    }
}
