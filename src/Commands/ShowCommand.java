package Commands;
import Collection.CollectionOfOrgs;
public class ShowCommand {
    public static void show(){
        System.out.println("Элементы коллекции: " + CollectionOfOrgs.getOrganizationVector());
    }
}
