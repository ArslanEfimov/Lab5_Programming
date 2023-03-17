package Commands;
import Collection.CollectionOfOrgs;
import Organization.Organization;
import java.util.Iterator;
public class RemoveFirstCommand {
    public void removeFirst() {
        Iterator<Organization> iter = CollectionOfOrgs.getOrganizationVector().iterator();
        if (CollectionOfOrgs.getOrganizationVector().size() != 0) {
            while (iter.hasNext()) {
                iter.next();
                iter.remove();
                break;
            }
        }
        else{
            System.out.println("Элементов в коллекции нет!");
        }

    }
}