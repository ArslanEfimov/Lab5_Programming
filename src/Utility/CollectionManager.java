package Utility;

import Comparators.OrganizationCompareAnnualTurn;
import Organization.Organization;
import ParceFile.FileManagerReader;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class CollectionManager {

    private Vector<Organization> organizationVector = new Vector<>();
    private FileManagerReader fileManagerReader;
    private LinkedList<Long> listForGenerateId = new LinkedList<>();
    public CollectionManager(){
    }
    public CollectionManager(FileManagerReader fileManagerReader){
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
    public void addIdToListForGenerate(Long id){
        listForGenerateId.add(id);
    }
    public LinkedList<Long> getListForGenerateId(){
        return listForGenerateId;
    }
    public Long generateId(){
        Long id;
        listForGenerateId = fileManagerReader.getId();
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

}
