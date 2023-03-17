package Commands;
import Collection.CollectionManager;
import Organization.Organization;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class UpdateIdCommand {
    public void updateId(){
        Iterator<Organization> iter = CollectionManager.getOrganizationVector().iterator();
        if(CollectionManager.getOrganizationVector().size()!=0){
            System.out.print("Введите значение id: ");
            Scanner scan = new Scanner(System.in);
            Long id = scan.nextLong();
            while(iter.hasNext()){
                Organization i = iter.next();
                if(Objects.equals(i.getId(),id)){
                    i.setId(Organization.generateId());
                }
            }
        }
        else{
            System.out.println("Элементов в коллекции нет");
        }
    }
}
