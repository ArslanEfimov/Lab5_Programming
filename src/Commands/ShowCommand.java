package Commands;
import Collection.CollectionManager;
public class ShowCommand {
    public static void show(){
        System.out.println("Элементы коллекции: " + CollectionManager.getOrganizationVector());
    }
}
