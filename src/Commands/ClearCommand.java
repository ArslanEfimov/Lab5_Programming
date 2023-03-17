package Commands;
import Collection.CollectionOfOrgs;
public class ClearCommand {
    public void clear(){
        CollectionOfOrgs.getOrganizationVector().clear();
        System.out.println("Коллекция успешно очищена");
    }
}
