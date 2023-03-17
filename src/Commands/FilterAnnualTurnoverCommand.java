package Commands;
import Collection.CollectionManager;
import Organization.Organization;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class FilterAnnualTurnoverCommand {
    public void filterAnnualTurnover(){
        Iterator<Organization> iter = CollectionManager.getOrganizationVector().iterator();
        if(CollectionManager.getOrganizationVector().size()!=0){
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
