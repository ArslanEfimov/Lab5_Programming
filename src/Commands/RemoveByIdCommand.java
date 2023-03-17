package Commands;

import Collection.CollectionOfOrgs;

import java.util.Scanner;

public class RemoveByIdCommand {
    public void removeById() {
        System.out.print("Введите значение id: ");
        Scanner scan = new Scanner(System.in);
        Long id = scan.nextLong();
        CollectionOfOrgs.removeById(id);
    }
}
