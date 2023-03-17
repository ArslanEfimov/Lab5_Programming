package Commands;

import Collection.CollectionManager;
import Organization.Organization;

import java.util.Iterator;
import java.util.Scanner;

public class RemoveGreaterCommand {
    public void removeGreater() {
        Scanner scan = new Scanner(System.in);
        if((CollectionManager.getOrganizationVector().size()) > 0){
            System.out.print("Введите значение annualTurnover: ");
            Float annualTurn = scan.nextFloat();
            int s = CollectionManager.getOrganizationVector().size();
            Iterator<Organization> iter = CollectionManager.getOrganizationVector().iterator();
            while (iter.hasNext()) {
                Organization i = iter.next();
                if (i.getAnnualTurnover() > annualTurn) {
                    iter.remove();
                }
            }
            if (CollectionManager.getOrganizationVector().size() < s) {
                System.out.println("Элемент/элементы успешно удален/ны");
            } else {
                System.out.println("Все элементы не превышают заданный");
            }
        }
        else{
            System.out.println("Элементов в коллекции нет");
        }

    }
}