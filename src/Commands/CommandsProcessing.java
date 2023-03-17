package Commands;

import Collection.CollectionManager;
import Exceptions.WrongValuesException;

import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

public class CommandsProcessing {
    String s;

    public void switchCommands() throws WrongValuesException {
        System.out.print("enter command: ");
        while (!Objects.equals(s,"exit")) {
            Scanner scanner = new Scanner(System.in);
            s = scanner.nextLine();
            switch (s) {
                case "help":
                    HelpCommand helpCommand = new HelpCommand();
                    helpCommand.help();
                    System.out.print("enter command: ");
                    break;
                case "info":
                    InfoCommand infoCommand = new InfoCommand();
                    infoCommand.info();
                    System.out.print("enter command: ");
                    break;
                case "exit":
                    System.out.println("До свидания");
                    System.exit(0);
                case "show":
                    ShowCommand showCommand = new ShowCommand();
                    showCommand.show();
                    System.out.print("enter command: ");
                    break;
                case "remove_by_id":
                    RemoveByIdCommand removeByIdCommand = new RemoveByIdCommand();
                    removeByIdCommand.removeById();
                    System.out.print("enter command: ");
                    break;
                case "clear":
                    ClearCommand clearCommand = new ClearCommand();
                    clearCommand.clear();
                    System.out.print("enter command: ");
                    break;
                case("remove_first"):
                    RemoveFirstCommand removeFirstCommand = new RemoveFirstCommand();
                    removeFirstCommand.removeFirst();
                    System.out.print("enter command: ");
                    break;
                case("filter_by_annual_turnover"):
                    FilterAnnualTurnoverCommand filterAnnualTurnoverCommand = new FilterAnnualTurnoverCommand();
                    filterAnnualTurnoverCommand.filterAnnualTurnover();
                    System.out.print("enter command: ");
                    break;
                case("update_by_id"):
                    UpdateIdCommand updateIdCommand = new UpdateIdCommand();
                    updateIdCommand.updateId();
                    System.out.print("enter command: ");
                    break;
                case("add_element"):
                    AddCommand addCommand = new AddCommand();
                    int k = CollectionManager.getOrganizationVector().size();
                    addCommand.addElem();
                    if (k < CollectionManager.getOrganizationVector().size()) System.out.println("Элемент успешно добавлен в коллекцию");
                    System.out.print("enter command: ");
                    break;
                case("print_descending"):
                    System.out.print("Элементы коллекции в порядке убывания: ");
                    Collections.sort(CollectionManager.getOrganizationVector(), new OrganizationCompareAnnualTurn());
                    System.out.println(CollectionManager.getOrganizationVector());
                    System.out.print("enter command: ");
                    break;
                case("remove_greater"):
                    RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand();
                    removeGreaterCommand.removeGreater();
                    System.out.print("enter command: ");
                    break;
                case("count_greater_than_official_address"):
                    CountGreaterThanOfficialAddressCommand countGreaterThanOfficialAddressCommand = new CountGreaterThanOfficialAddressCommand();
                    countGreaterThanOfficialAddressCommand.countGreaterThanOfficAddress();
                    System.out.print("enter command: ");
                    break;
                case("add_if_max_element"):
                    AddIfMaxCommand addIfMaxCommand = new AddIfMaxCommand();
                    addIfMaxCommand.addIfMax();
                    System.out.print("enter command: ");
                    break;
                default:
                    System.out.println("Такой команды нет, воспользуйтесь командой help, чтобы увидеть список команд");
                    System.out.print("enter command: ");
            }
        }
    }

}