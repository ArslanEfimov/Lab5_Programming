package Utility;
import Comparators.OrganizationCompareAnnualTurn;
import Exceptions.*;
import Organization.*;
import ParceFile.FileManagerReader;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * collection class
 */
@XmlRootElement(name = "organizations")
public class CollectionManager {
    @XmlElement(name = "organization")
    private Vector<Organization> organizationVector = new Vector<>();
    private FileManagerReader fileManagerReader;
    private LinkedList<Long> listForGenerateId = new LinkedList<>();
    private ConsoleManager consoleManager = new ConsoleManager();
    public CollectionManager(){
    }
    public CollectionManager(FileManagerReader fileManagerReader) {
        this.fileManagerReader = fileManagerReader;
        organizationVector = fileManagerReader.readCollection();
    }

    /**
     * basic setter for Organization collection
     * @param organizationVector
     */
    public void setOrganizationVector (Vector <Organization> organizationVector) {
        this.organizationVector = organizationVector;
    }

    /**
     * basic getter for Organization collection
     * @return organizationVector
     */
    public Vector<Organization> getCollectinVector(){
        return organizationVector;
    }

    /**
     * method for clear collection
     */
    public void clear() {
        organizationVector.clear();
    }

    /**
     * method to get an iterator
     * @return organizationVector.iterator()
     */
    public Iterator<Organization> getIterator(){
        return organizationVector.iterator();
    }

    /**
     * method to get collection size
     * @return size collection
     */
    public int getCollectionSize(){
        return organizationVector.size();
    }

    /**
     * method to get collection first element
     * @return first element in collection
     */
    public Organization getFirstElement(){
        return organizationVector.firstElement();
    }

    /**
     * a method that shows the elements of a collection
     */
    public void show(){
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.println("Collection elements: \n" + organizationVector);
    }

    /**
     * a method to check that an organization with this id exists
     * @param id
     * @return organization or null
     */
    public Organization getById(Long id){
        for(Organization organization: organizationVector){
            if(organization.getId().equals(id)) return organization;
        }
        return null;
    }

    /**
     * a method to check that an organization with this annualTurnover exists
     * @param annualTurnover
     * @return organization or null
     */
    public Organization getByAnnualTurnover(Float annualTurnover){
        for(Organization organization: organizationVector){
            if(organization.getAnnualTurnover().equals(annualTurnover)) return organization;
        }
        return null;
    }

    /**
     * a method that returns the elements of a collection in descending order
     * @return sorted organizationVector
     */
    public Vector<Organization> printDescending(){
        Collections.sort(organizationVector, new OrganizationCompareAnnualTurn());
        return organizationVector;
    }

    /**
     * method which add element in collection
     * @param organization
     */
    public void addNewElement(Organization organization){
        organizationVector.add(organization);
    }
    public LinkedList<Long> getListForGenerateId(){
        return listForGenerateId;
    }

    /**
     * method for generate id
     * @return id
     */
    public Long generateId(){
        Long id;
        fillListId();
        boolean flag = true;
        while (flag) {
            id = (long) (1L + (Math.random() * (listForGenerateId.size()+1)));
            if (!listForGenerateId.contains(id)) {
                listForGenerateId.add(id);
                return id;
            }
        }
        return null;
    }

    /**
     * iterator for add_if_max command
     * @param organization
     * @return boolean
     */
    public boolean iteratorForAddIfMax(Organization organization){
        Iterator<Organization> iter = getIterator();
        int count = 0;
        while(iter.hasNext()){
            Organization org = iter.next();
            int result = organization.getAnnualTurnover().compareTo(org.getAnnualTurnover());
            if(result > 0){
                count+=1;
            }
        }
        if(count == getCollectionSize()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * iterator for count_greater_than_official_address command
     * @param officialAddress
     */
    public void iteratorForCountGreaterThanOfficAddr(String officialAddress){
        Iterator<Organization> iter = getIterator();
        int j = 0;
        while (iter.hasNext()) {
            Organization i = iter.next();
            int result = i.getOfficialAddress().getStreet().compareTo(officialAddress);
            if (result > 0) {
                j += 1;
            }
        }
        consoleManager.println("Count of elements: " + j);
    }

    /**
     * iterator for filter_by_annual_turnover command
     * @param annualTur
     */
    public void iteratorForFilterAnnualTurnover(Float annualTur){
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            Organization organization = iter.next();
            if (Objects.equals(organization.getAnnualTurnover(), annualTur)) {
                consoleManager.println(organization);
            }
        }
    }

    /**
     * iterator for remove_by_id command
     * @param id
     * @return boolean
     */
    public boolean iteratorForRemoveById(Long id){
        Iterator<Organization> iter = getIterator();
        int i = 0;
        while (iter.hasNext()) {
            if (Objects.equals(iter.next().getId(), id)) {
                iter.remove();
                i += 1;

            }
        }
        if(i==1){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * iterator for remove_first command
     */
    public void iteratorRemoveFirstElement(){
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
            break;
        }
    }

    /**
     * iterator for remove_greater command
     * @param annualTurn
     */
    public void iteratorRemoveGreater(Float annualTurn){
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            Organization organization = iter.next();
            if (organization.getAnnualTurnover() > annualTurn) {
                iter.remove();
            }
        }
    }

    /**
     * iterator for update_by_id command
     * @param id
     */
    public void iteratorForUpdateId(Long id) {
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            Organization organization = iter.next();
            if (Objects.equals(organization.getId(), id)) {
                organization.setId(generateId());
            }
        }
    }

    /**
     * method for filling collection id with identifiers
     */
    public void fillListId(){
        for(Organization organization : organizationVector){
            listForGenerateId.add(organization.getId());
        }
    }

    /**
     * a method that checks for a match of id in the collection
     * @param id
     * @return boolean
     */
    public boolean checkId(Long id){
            if (listForGenerateId.contains(id)) {
                return true;
            }
            else{
                return false;
            }

    }

    public void methodForAddIfMax(Organization organization){
        if(iteratorForAddIfMax(organization)){
            addNewElement(organization);
            consoleManager.println("organization has been successfully added to the collection!");
        }
        else{
            consoleManager.println("The element was not added because its value is less than the largest element");
        }
    }

    public void methodForUpdateId(Long id) {
        try {
            if (getCollectionSize() != 0) {
                if (id > 0) {
                    if (getById(id) == null) throw new OrganizationNotFoundException();
                    iteratorForUpdateId(id);
                    consoleManager.println("element id updated successfully");
                } else {
                    consoleManager.println("id must be greater than 0");
                }
            } else {
                consoleManager.println("There are no items in the collection");
            }
        }catch(OrganizationNotFoundException ex){
            consoleManager.println("organization with this id was not found");
            }
    }

    public void methodForRemoveGreater(Float annualTurn){
        if (getCollectionSize() > 0) {
            if(annualTurn > 0) {
                int size = getCollectionSize();
                iteratorRemoveGreater(annualTurn);
                if (getCollectionSize() < size) {
                    consoleManager.println("Element(s) successfully removed(s)");
                } else {
                    consoleManager.println("All elements do not exceed the specified");
                }
            }else{
                consoleManager.println("The value of the annual turnover must be greater than 0");
            }
        } else {
            consoleManager.println("There are no elements in the collection");
        }
    }

    public void methodForRemoveById(Long id) throws OrganizationNotFoundException {
        if(getCollectionSize()!=0) {
            if(id>0) {
                if (getById(id) == null) throw new OrganizationNotFoundException();
                if(iteratorForRemoveById(id)==true){
                    consoleManager.println("Element deleted successfully");
                }
                else{
                    consoleManager.println("This id is not in the collection");
                }
            }else{
                consoleManager.println("id must be greater than 0");
            }
        }else{
            consoleManager.println("There are no elements in the collection");
        }
    }

    public void methodForFilterAnnualTurnover(Float annualTur) throws OrganizationNotFoundException {
        if (getCollectionSize() != 0) {
            if (annualTur > 0) {
                if (getByAnnualTurnover(annualTur) == null) throw new OrganizationNotFoundException();
                iteratorForFilterAnnualTurnover(annualTur);
            }else{
                consoleManager.println("The value of the annual turnover must be greater than 0");
            }
        } else {
            consoleManager.println("There are no items in the collection");
        }
    }

    public void methodForCountGreaterThanOfficialAddress() throws NotInDeclaredLimitsException, MustNotBeEmptyException {
        if (getCollectionSize() != 0) {
            consoleManager.print("enter address: ");
            String officialAddress = consoleManager.readString().trim();
            if (officialAddress.isEmpty()) throw new MustNotBeEmptyException();
            if(officialAddress.length()>130) throw new NotInDeclaredLimitsException();
            iteratorForCountGreaterThanOfficAddr(officialAddress);
        } else {
            consoleManager.println("There are no elements in the collection");
        }
    }

    /**
     * a method to check if the values in the file are correct
     * @param vectorOrganization
     */
    public void checkCollection(Vector<Organization> vectorOrganization){
        ConsoleManager consoleManager = new ConsoleManager();
        try{
            for(Organization organization : vectorOrganization){
                organization.setId(organization.getId());
                if(checkId(organization.getId())) throw new IncorrectValueException("id must be unique");
                listForGenerateId.add(organization.getId());
                organization.setName(organization.getName());
                organization.getCoordinates().setX(organization.getCoordinates().getX());
                organization.getCoordinates().setY(organization.getCoordinates().getY());
                organization.setCreationDate(organization.getCreationDate());
                organization.setType(organization.getType());
                organization.setFullName(organization.getFullName());
                organization.getOfficialAddress().setStreet(organization.getOfficialAddress().getStreet());
                organization.setAnnualTurnover(organization.getAnnualTurnover());
            }
            this.organizationVector = vectorOrganization;
        }catch (IllegalArgumentException | IncorrectValueException ex){
            consoleManager.println(ex.getMessage());
            consoleManager.println("The data in the file is incorrect, check it, you may not have the necessary tags");
            this.organizationVector = new Vector<>();

        }
    }


}
