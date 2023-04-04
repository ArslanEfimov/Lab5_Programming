package Utility;

import Comparators.OrganizationCompareAnnualTurn;
import Exceptions.IncorrectValueException;
import Organization.*;
import ParceFile.FileManagerReader;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;
@XmlRootElement(name = "organizations")
public class CollectionManager {
    @XmlElement(name = "organization")
    private Vector<Organization> organizationVector = new Vector<>();
    private FileManagerReader fileManagerReader;
    private LinkedList<Long> listForGenerateId = new LinkedList<>();
    public CollectionManager(){
    }
    public CollectionManager(FileManagerReader fileManagerReader) {
        this.fileManagerReader = fileManagerReader;
        organizationVector = fileManagerReader.readCollection();
    }

    public void setOrganizationVector (Vector <Organization> organizationVector) {
        this.organizationVector = organizationVector;
    }
    public Vector<Organization> getCollectinVector(){
        return organizationVector;
    }

    public void clear() {
        organizationVector.clear();
    }
    public Iterator<Organization> getIterator(){
        return organizationVector.iterator();
    }
    public int getCollectionSize(){
        return organizationVector.size();
    }
    public Organization getFirstElement(){
        return organizationVector.firstElement();
    }
    public void show(){
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.println("Collection elements: \n" + organizationVector);
    }
    public Organization getById(Long id){
        for(Organization organization: organizationVector){
            if(organization.getId().equals(id)) return organization;
        }
        return null;
    }
    public Organization getByAnnualTurnover(Float annualTurnover){
        for(Organization organization: organizationVector){
            if(organization.getAnnualTurnover().equals(annualTurnover)) return organization;
        }
        return null;
    }

    public Vector<Organization> printDescending(){
        Collections.sort(organizationVector, new OrganizationCompareAnnualTurn());
        return organizationVector;
    }
    public void addNewElement(Organization organization){
        organizationVector.add(organization);
    }
    public LinkedList<Long> getListForGenerateId(){
        return listForGenerateId;
    }
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

    public void iteratorForCountGreaterThanOfficAddr(String officialAddress){
        ConsoleManager consoleManager = new ConsoleManager();
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

    public void iteratorForFilterAnnualTurnover(Float annualTur){
        ConsoleManager consoleManager = new ConsoleManager();
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            Organization organization = iter.next();
            if (Objects.equals(organization.getAnnualTurnover(), annualTur)) {
                consoleManager.println(organization);
            }
        }
    }

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
    public void iteratorRemoveFirstElement(){
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
            break;
        }
    }
    public void iteratorRemoveGreater(Float annualTurn){
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            Organization organization = iter.next();
            if (organization.getAnnualTurnover() > annualTurn) {
                iter.remove();
            }
        }
    }
    public void iteratorForUpdateId(Long id) {
        Iterator<Organization> iter = getIterator();
        while (iter.hasNext()) {
            Organization organization = iter.next();
            if (Objects.equals(organization.getId(), id)) {
                organization.setId(generateId());
            }
        }
    }
    public void fillListId(){
        for(Organization organization : organizationVector){
            listForGenerateId.add(organization.getId());
        }
    }
    public boolean checkId(Long id){
            if (listForGenerateId.contains(id)) {
                return true;
            }
            else{
                return false;
            }

    }

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
                organization.setOfficialAddress(organization.getOfficialAddress());
                organization.setAnnualTurnover(organization.getAnnualTurnover());
            }
            this.organizationVector = vectorOrganization;
        }catch (IllegalArgumentException | IncorrectValueException ex){
            consoleManager.println(ex.getMessage());
            consoleManager.println(listForGenerateId);
            consoleManager.println("The data in the file is incorrect, check it, you may not have the necessary tags");
            this.organizationVector = new Vector<>();

        }
    }

}
