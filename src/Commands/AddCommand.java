package Commands;

import Collection.CollectionOfOrgs;
import Exceptions.WrongValuesException;
import Organization.Address;
import Organization.Coordinates;
import Organization.Organization;
import Organization.OrganizationType;

import java.time.LocalDate;
import java.util.Scanner;

public class AddCommand {
    public Organization elementAdding() throws WrongValuesException {
        Scanner scan1 = new Scanner(System.in);
        System.out.print("Введите значение поля name: ");
        String name = scan1.nextLine();
        while (name.isEmpty()) {
            System.out.println("Имя не может быть пустым, введите его заново");
            System.out.print("Введите значение поля name: ");
            name = scan1.next();
        }
        Scanner scan2 = new Scanner(System.in);
        System.out.print("Введите значение поля fullName: ");
        String fullName = scan2.nextLine();
        Scanner scan3 = new Scanner(System.in);
        System.out.print("Введите значение поля officialAddres: ");
        String officialAddress = scan3.nextLine();
        while (officialAddress.length() > 130) {
            System.out.println("Длина адреса не может быть больше 130, введите новый адрес");
            officialAddress = scan3.nextLine();
        }
            Float x = null;
            try {
                Scanner scan4 = new Scanner(System.in);
                System.out.print("Введите значение координаты x: ");
                x = scan4.nextFloat();
            } catch (java.util.InputMismatchException e) {
                Scanner scan4 = new Scanner(System.in);
                System.out.print("Значение координаты x должно быть вещественным числом, введите еще раз: ");
                x = scan4.nextFloat();
            }
            Integer y = null;
            try {
                Scanner scan5 = new Scanner(System.in);
                System.out.print("Введите значение координаты y: ");
                y = scan5.nextInt();
            } catch (java.util.InputMismatchException e){
                Scanner scan5 = new Scanner(System.in);
                System.out.print("Значение координаты y должно быть целым числом, введите еще раз: ");
                y = scan5.nextInt();
            }
            Float annualTurnover = null;
            try {
                Scanner scan6 = new Scanner(System.in);
                System.out.print("Введите значение поля annualTurnover: ");
                annualTurnover = scan6.nextFloat();
            }catch (java.util.InputMismatchException e){
                Scanner scan6 = new Scanner(System.in);
                System.out.print("Значение годового оборота(annualTurnover) должно быть вещественным числом, введите еще раз: ");
                annualTurnover = scan6.nextFloat();
            }
        Scanner scan7 = new Scanner(System.in);
        while (annualTurnover <= 0) {
            System.out.println("Годовой оборот не может быть меньше 0, введите значение еще раз");
            annualTurnover = scan7.nextFloat();
        }

        Scanner scan8 = new Scanner(System.in);
        System.out.println("Введите цифру типа той организации, которую хотите выбрать: " + "\n" +
                "1.COMMERCIAL" + "\n" + "2.PUBLIC" + "\n" + "3.GOVERNMENT" + "\n" + "4.TRUST" + "\n" + "5.PRIVATE_LIMITED_COMPANY");
        Integer orgNumber = scan8.nextInt();
        while ((orgNumber != 1) && (orgNumber != 2) && (orgNumber != 3) && (orgNumber != 4) && (orgNumber != 5)) {
            System.out.println("Enter again, input incorrect (choose int num from 1 to 5)");
            orgNumber = scan8.nextInt();
        }
        OrganizationType type = null;
        if (orgNumber == 1) {
            type = OrganizationType.COMMERCIAL;
        }
        if (orgNumber == 2) {
            type = OrganizationType.PUBLIC;
        }
        if (orgNumber == 3) {
            type = OrganizationType.GOVERNMENT;
        }
        if (orgNumber == 4) {
            type = OrganizationType.TRUST;
        }
        if (orgNumber == 5) {
            type = OrganizationType.PRIVATE_LIMITED_COMPANY;
        }

        Organization org = new Organization();
        org.setId(Organization.generateId());
        org.setName(name);
        org.setCoordinates(new Coordinates(x, y));
        org.setType(type);
        org.setAnnualTurnover(annualTurnover);
        org.setOfficialAddress(new Address(officialAddress));
        org.setFullName(fullName);
        org.setCreationDate(LocalDate.now());
        return org;
    }
    public void addElem() throws WrongValuesException {
        AddCommand addCommand = new AddCommand();
        CollectionOfOrgs.getOrganizationVector().add(addCommand.elementAdding());
    }
}
