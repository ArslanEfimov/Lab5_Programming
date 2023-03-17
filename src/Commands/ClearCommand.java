package Commands;
import Collection.CollectionManager;
public class ClearCommand {
    public void clear(){
        CollectionManager.getOrganizationVector().clear();
        System.out.println("Коллекция успешно очищена");
    }
}
