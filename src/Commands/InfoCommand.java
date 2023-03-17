package Commands;
import Collection.CollectionOfOrgs;
public class InfoCommand {
    public  void info() {
        System.out.println("Тип коллекции: Organization");
        System.out.println("Количество элементов в коллекции: " + CollectionOfOrgs.getOrganizationVector().size());
        if (CollectionOfOrgs.getOrganizationVector().size() != 0) {
            System.out.println("Дата инициализации: " + CollectionOfOrgs.getOrganizationVector().firstElement().getCreationDate());
        }
    }
}