package ParceFile;

import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Organization.Address;
import Organization.Coordinates;
import Organization.Organization;
import Organization.OrganizationType;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileManagerReader {
    private final ArrayDeque<String> value;
    private final Vector<Organization> resultOrganizations;
    private final ArrayList<String> valuenew;
    private final ArrayList<String> keys;
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private String fileName;


    public FileManagerReader(String fileName) {
        resultOrganizations = new Vector<>();
        value = new ArrayDeque<>();
        valuenew = new ArrayList<>();
        keys = new ArrayList<>();
        consoleManager = new ConsoleManager();
        collectionManager = new CollectionManager();
        this.fileName = fileName;
    }

    public Vector<Organization> readCollection() throws IOException,NumberFormatException{
        Vector<Organization> collection = new Vector<>();
        try (Scanner scanner = new Scanner(Paths.get(fileName))){
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                String[] arrayValue = line.split("<.*?>");
                addValue(arrayValue);
                Pattern patternTegs = Pattern.compile("<[^/]+>");
                Matcher matcher = patternTegs.matcher(line);
                if (matcher.find()) {
                    keys.add(matcher.group().substring(1, matcher.end() - 1));
                }
            }
            valuenew.addAll(value);
        }catch(NoSuchElementException exception){
            consoleManager.println("Boot file is empty!");
        }
        catch (NoSuchFileException ex){
            consoleManager.println("Invalid file name specified! Please enter a valid name");
            consoleManager.exit();
        }catch(AccessDeniedException ex){
        consoleManager.println(" File reading failed because application cannot access to this file.");
        consoleManager.exit();
    }
        int elenemtCount = keys.size()/12;
        int count = 0;
        try {
            for (int i = 0; i < elenemtCount; i++) {
                Organization organization = new Organization();
                organization.setId(Long.parseLong(valuenew.get(count)));
                collectionManager.addIdToListForGenerate(Long.parseLong(valuenew.get(count)));
                if(Long.parseLong(valuenew.get(count))<0) throw new NotInDeclaredLimitsException();
                try {
                    if (valuenew.get(count).isEmpty()) throw new MustNotBeEmptyException();
                }catch (MustNotBeEmptyException ex){
                    consoleManager.println("id not cannot be empty");
                }
                try {
                    organization.setName(valuenew.get(count + 1));
                    if(valuenew.get(count + 1).isEmpty()) throw new MustNotBeEmptyException();
                }catch(MustNotBeEmptyException ex){
                    consoleManager.println("name cannot be empty");
                }
                organization.setCoordinates(new Coordinates((float) Double.parseDouble(valuenew.get(count + 2)), (int) Double.parseDouble(valuenew.get(count + 3))));
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                organization.setCreationDate(LocalDate.parse(valuenew.get(count+4),dateFormatter));
                if(LocalDate.parse(valuenew.get(count+4),dateFormatter).getYear()>LocalDate.now().getYear()) throw new NotInDeclaredLimitsException();
                organization.setAnnualTurnover((float) Double.parseDouble(valuenew.get(count + 5)));
                if (valuenew.get(count + 6).equals("")) {
                    organization.setFullName(null);
                } else {
                    organization.setFullName(valuenew.get(count + 6));
                }
                try {

                    if (valuenew.get(count + 7).equals("коммерческий")) {
                        organization.setType(OrganizationType.COMMERCIAL);
                    }
                    if (valuenew.get(count + 7).equals("публичный")) {
                        organization.setType(OrganizationType.PUBLIC);
                    }
                    if (valuenew.get(count + 7).equals("правительство")) {
                        organization.setType(OrganizationType.COMMERCIAL);
                    }
                    if (valuenew.get(count + 7).equals("доверие")) {
                        organization.setType(OrganizationType.TRUST);
                    }
                    if (valuenew.get(count + 7).equals("закрытое акционерное общество")) {
                        organization.setType(OrganizationType.PRIVATE_LIMITED_COMPANY);
                    }
                    if(valuenew.get(count + 7).isEmpty()) throw new MustNotBeEmptyException();
                }catch (MustNotBeEmptyException ex) {
                    consoleManager.println("organization type cannot be empty");
                }
                    if (valuenew.get(count + 8).equals("")) {
                        organization.setOfficialAddress(null);
                    } else {
                        organization.setOfficialAddress(new Address(valuenew.get(count + 8)));
                    }
                count = count + 9;
                resultOrganizations.add(organization);
                collection = resultOrganizations;
                }
        }catch(NumberFormatException ex){
            consoleManager.println("The file has been corrupted or it contains/contains incorrect/incorrect format/s of values!");
            consoleManager.exit();
        }catch (NotInDeclaredLimitsException ex){
            consoleManager.println("the limit of some values does not meet the requirements");
            consoleManager.exit();
        }catch (DateTimeParseException ex) {
            consoleManager.println("contains invalid date/dates");
            consoleManager.exit();
        }
        return collection;
        }

    private void addValue(String[] arrayValue){
        for(String v: arrayValue){
            value.addLast(v);
        }
    }
    public List<String> getKeys(){
        return keys.subList(1,12);
    }
    public LinkedList<Long> getId(){
        return collectionManager.getListForGenerateId();
    }

    public String getFileName(){
        return fileName;
    }
}
