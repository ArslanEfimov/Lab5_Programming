package ParceFile;

import Collection.CollectionOfOrgs;
import Exceptions.WrongValuesException;
import Organization.Address;
import Organization.Coordinates;
import Organization.Organization;
import Organization.OrganizationType;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileReader extends CollectionOfOrgs{
    private final ArrayDeque<String> value;
    private final Vector<Organization> resultOrganizations;
    private final ArrayList<String> valuenew;
    private final ArrayList<String> keys;

    public FileReader() {
        resultOrganizations = new Vector<>();
        value = new ArrayDeque<>();
        valuenew = new ArrayList<>();
        keys = new ArrayList<>();
    }

    public void readCollection(String path) throws IOException, WrongValuesException,NumberFormatException {
        try (Scanner scanner = new Scanner(Paths.get(path))){
            scanner.nextLine();
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
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
            System.out.println("Загрузочный файл пуст!");
            System.exit(0);
        }
        catch (NoSuchFileException ex){
            System.out.println("Указано неверное имя файла! Введите правильное имя");
            System.exit(0);
        }


        int elenemtCount = keys.size()/12;
        int count = 0;
        try {
            for (int i = 0; i < elenemtCount; i++) {
                Organization organization = new Organization();
                organization.setId(Organization.generateId());
                organization.setName(valuenew.get(count));
                organization.setCoordinates(new Coordinates((float) Double.parseDouble(valuenew.get(count + 1)), (int) Double.parseDouble(valuenew.get(count + 2))));
                organization.setCreationDate(LocalDate.now());
                organization.setAnnualTurnover((float) Double.parseDouble(valuenew.get(count + 3)));

                organization.setFullName(valuenew.get(count + 4));
                if (valuenew.get(count + 5).equals("COMMERCIAL")) {
                    organization.setType(OrganizationType.COMMERCIAL);
                }
                if (valuenew.get(count + 5).equals("PUBLIC")) {
                    organization.setType(OrganizationType.PUBLIC);
                }
                if (valuenew.get(count + 5).equals("GOVERNMENT")) {
                    organization.setType(OrganizationType.COMMERCIAL);
                }
                if (valuenew.get(count + 5).equals("TRUST")) {
                    organization.setType(OrganizationType.TRUST);
                }
                if (valuenew.get(count + 5).equals("PRIVATE_LIMITED_COMPANY")) {
                    organization.setType(OrganizationType.PRIVATE_LIMITED_COMPANY);
                }
                organization.setOfficialAddress(new Address(valuenew.get(count + 6)));
                count = count + 7;
                resultOrganizations.add(organization);
                setOrganizationVector(resultOrganizations);

            }
        }catch (NumberFormatException ex){
            System.out.println("Файл был испорчен или в нем содержится/содержатся неверный/неверные формат/ы значений!");
            System.exit(0);
        }


    }
    private void addValue(String[] arrayValue){
        for(String v: arrayValue){
            value.addLast(v);
        }
    }

}
