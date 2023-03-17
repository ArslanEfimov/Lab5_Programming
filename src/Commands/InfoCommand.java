package Commands;
import Collection.CollectionManager;
public class InfoCommand {
    public  void info() {
        System.out.println("Тип коллекции: Organization");
        System.out.println("Количество элементов в коллекции: " + CollectionManager.getOrganizationVector().size());
        if (CollectionManager.getOrganizationVector().size() != 0) {
            System.out.println("Дата инициализации: " + CollectionManager.getOrganizationVector().firstElement().getCreationDate());
        }
    }
}