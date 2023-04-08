package Utility;

import Exceptions.IncorrectValueException;
import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Organization.Address;
import Organization.Coordinates;
import Organization.Organization;
import Organization.OrganizationType;

import java.time.LocalDate;

/**
 * builds an object of type Organization
 */
public class AskerOrganizations {
    private ConsoleManager consoleManager;
    private CollectionManager collectionManager;

    public AskerOrganizations(CollectionManager collectionManager) {
        this.consoleManager = new ConsoleManager();
        this.collectionManager = collectionManager;
    }

    /**
     * set correct name for Organization
     * @return name
     * @exception MustNotBeEmptyException
     */
    public String setName(){
        String name;
        while (true) {
            try {
                consoleManager.print("enter name: ");
                name = consoleManager.readString().trim();
                if (name.isEmpty()) throw new MustNotBeEmptyException();
                break;
            } catch (MustNotBeEmptyException exception) {
                consoleManager.println("name cannot be empty, please re-enter");
            }
        }
        return name;
    }

    /**
     * set correct x coordinate for Organization
     * @return x
     * @exception NumberFormatException
     */
    public Float setX() {
        Float x;
        while (true) {
            try {
                consoleManager.print("enter x coordinate: ");
                x = Float.parseFloat(consoleManager.readString().trim().replace(",","."));
                break;
            } catch (NumberFormatException ex) {
                consoleManager.println("x coordinate must be float type");
            }
        }
        return x;

    }

    /**
     * set correct y coordinates for Organization
     * @return y
     * @exception NumberFormatException
     */
    public int setY() {
        int y;
        while (true) {
            try {
                consoleManager.print("enter y coordinate: ");
                y = Integer.parseInt(consoleManager.readString().trim());
                if(y<=-98)  throw new NotInDeclaredLimitsException();
                break;
            } catch (NumberFormatException ex) {
                consoleManager.println("y coordinate must be int type");
            }catch (NotInDeclaredLimitsException ex){
                consoleManager.println("y must be greater than -98");
            }
        }
        return y;
    }

    /**
     * set Coordinates
     * @return Coordinates(x, y)
     */
    public Coordinates setCoordinates(){
        Float x = setX();
        int y = setY();
        return new Coordinates(x, y);
    }

    /**
     * set correct annualTurnover for Organization
     * @return annualTurnover
     * @exception NotInDeclaredLimitsException
     * @exception NumberFormatException
     */
    public Float setAnnualTurnover(){
        Float annualTrunover;
        while (true) {
            try {
                consoleManager.print("enter the value of the annual turnover: ");
                annualTrunover = Float.parseFloat(consoleManager.readString().trim().replace(",","."));
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

    /**
     * set correct fullName for Organization
     * @return fullName
     */
    public String setFullName() {
        String fullName;
        consoleManager.print("enter fullName: ");
        fullName = consoleManager.readString().trim();
        if (fullName.isEmpty()) {
            return null;
        }
        return fullName;
    }

    /**
     * set correct street
     * @return street
     * @exception NotInDeclaredLimitsException
     * @exception MustNotBeEmptyException
     */
    public String setStreet(){
        String street;
        while (true) {
            try {
                consoleManager.print("enter street: ");
                street = consoleManager.readString().trim();
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

    /**
     * set officialAddress
     * @return officialAddress
     */
    public Address setOfficialAddress(){
        Address officialAddress = new Address(setStreet());
        return officialAddress;
    }

    /**
     * set correct type of Organization
     * @return type
     * @exception NumberFormatException
     * @exception NotInDeclaredLimitsException
     */
    public OrganizationType setType(){
        consoleManager.println("  1.COMMERCIAL  \n  2.PUBLIC  \n  3.GOVERNMENT  \n  4.TRUST \n 5.PRIVATE_LIMITED_COMPANY");
        int orgNumber;
            while (true) {
                try {
                consoleManager.print("enter the number of the type of organization you need: ");
                orgNumber = Integer.parseInt(consoleManager.readString().trim());
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

    public void setOrganization(Organization organization) {
        try {
            organization.setId(collectionManager.generateId());
            organization.setName(setName());
            organization.setCoordinates(setCoordinates());
            organization.setCreationDate(LocalDate.now());
            organization.setAnnualTurnover(setAnnualTurnover());
            organization.setFullName(setFullName());
            organization.setOfficialAddress(setOfficialAddress());
            organization.setType(setType());
        }catch (IncorrectValueException e) {
            consoleManager.println("incorrect values");
        }

    }

}
