package Utility;

import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Organization.Address;
import Organization.Coordinates;
import Organization.OrganizationType;

public class AskerOrganizations {
    private ConsoleManager consoleManager;

    public AskerOrganizations() {
        this.consoleManager = new ConsoleManager();
    }

    public String setName(){
        String name;
        while (true) {
            try {
                consoleManager.print("enter name: ");
                name = consoleManager.readString();
                if (name.isEmpty()) throw new MustNotBeEmptyException();
                break;
            } catch (MustNotBeEmptyException exception) {
                consoleManager.println("name cannot be empty, please re-enter");
            }
        }
        return name;
    }

    public Float setX() {
        Float x;
        while (true) {
            try {
                consoleManager.print("enter x coordinate: ");
                x = Float.parseFloat(consoleManager.readString());
                break;
            } catch (NumberFormatException ex) {
                consoleManager.println("x coordinate must be float type");
            }
        }
        return x;

    }

    public int setY() {
        int y;
        while (true) {
            try {
                consoleManager.print("enter y coordinate: ");
                y = Integer.parseInt(consoleManager.readString());
                break;
            } catch (NumberFormatException ex) {
                consoleManager.println("y coordinate must be int type");
            }
        }
        return y;
    }

    public Coordinates setCoordinates(){
        Float x = setX();
        int y = setY();
        return new Coordinates(x, y);
    }

    public Float setAnnualTurnover(){
        Float annualTrunover;
        while (true) {
            try {
                consoleManager.print("enter the value of the annual turnover: ");
                annualTrunover = Float.parseFloat(consoleManager.readString());
                if (annualTrunover < 0) throw new NotInDeclaredLimitsException();
                break;
            } catch (NotInDeclaredLimitsException ex) {
                consoleManager.println("value does not match the specified limit");
            } catch (NumberFormatException ex) {
                consoleManager.println("value annualTurnover must be float type");
            }
        }
        return annualTrunover;
    }

    public String setFullName() {
        String fullName;
        consoleManager.print("enter fullName: ");
        fullName = consoleManager.readString();
        if (fullName.isEmpty()) {
            return null;
        }
        return fullName;
    }
    public String setStreet(){
        String street;
        while (true) {
            try {
                consoleManager.print("enter street: ");
                street = consoleManager.readString();
                if (street.isEmpty()) throw new MustNotBeEmptyException();
                if (street.length() > 130) throw new NotInDeclaredLimitsException();
                break;
            } catch (NotInDeclaredLimitsException ex) {
                consoleManager.println("string length must not exceed 130");
            }catch (MustNotBeEmptyException ex){
                consoleManager.println("cannot be empty");
            }
        }
        return street;

    }
    public Address setOfficialAddress(){
        Address officialAddress = new Address(setStreet());
        return officialAddress;
    }

    public OrganizationType setType(){
        consoleManager.println("  1.COMMERCIAL  \n  2.PUBLIC  \n  3.GOVERNMENT  \n  4.TRUST \n 5.PRIVATE_LIMITED_COMPANY");
        int orgNumber;
            while (true) {
                try {
                consoleManager.print("enter the number of the type of organization you need: ");
                orgNumber = Integer.parseInt(consoleManager.readString());
                if (orgNumber < 0 || orgNumber > 5) throw new NotInDeclaredLimitsException();
                if (orgNumber == 1) {
                    return OrganizationType.COMMERCIAL;
                }
                if (orgNumber == 2) {
                    return OrganizationType.PUBLIC;
                }
                if (orgNumber == 3) {
                    return OrganizationType.GOVERNMENT;
                }
                if (orgNumber == 4) {
                    return OrganizationType.TRUST;
                }
                if (orgNumber == 5) {
                    return OrganizationType.PRIVATE_LIMITED_COMPANY;
                }
            }catch(NumberFormatException ex){
                consoleManager.println("number must be int");
            }
            catch(NotInDeclaredLimitsException ex){
                consoleManager.println("enter again, input incorrect (choose int num from 1 to 5)");
            }
        }

    }


}
