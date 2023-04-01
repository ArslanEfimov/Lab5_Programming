package ParceFile;

import Exceptions.MustNotBeEmptyException;
import Exceptions.NotInDeclaredLimitsException;
import Exceptions.XmlTegsWrongValuesException;
import Organization.Address;
import Organization.Coordinates;
import Organization.Organization;
import Organization.OrganizationType;
import Utility.CollectionManager;
import Utility.ConsoleManager;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class FileManagerReader {
    private final Vector<Organization> resultOrganizations;
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private String fileName;


    public FileManagerReader(String fileName) {
        resultOrganizations = new Vector<>();
        consoleManager = new ConsoleManager();
        collectionManager = new CollectionManager();
        this.fileName = fileName;
    }

    public Vector<Organization> readCollection(){
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        Organization organization = null;
        String[] tegs = new String[]{"id","name","coordinates","x","y","creationDate","annualTurnover", "type", "officialAddress", "street","fullName"};
        List<String> listTegs = new ArrayList<>();
        for(String list : tegs){
            listTegs.add(list);
        }
        try{
            FileInputStream fileInputStream = new FileInputStream(fileName);
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(fileInputStream);
            if(fileInputStream.read()==-1) throw new NoSuchElementException();
            int count = 0;
            while(reader.hasNext()){
                XMLEvent xmlEvent = reader.nextEvent();
                if(xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    if(startElement.getName().getLocalPart().equals("organizations"+count)){
                        organization = new Organization();
                    }
                    if((!listTegs.contains(startElement.getName().getLocalPart())) && (!startElement.getName().getLocalPart().equals("organizations"+count))
                        && (!startElement.getName().getLocalPart().equals("organizations")) && (!startElement.isStartDocument())) throw new XmlTegsWrongValuesException();
                        if (startElement.getName().getLocalPart().equals("id")) {
                            xmlEvent = reader.nextEvent();
                            if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                            if((Long.parseLong(xmlEvent.asCharacters().getData().trim()))<0) throw new NotInDeclaredLimitsException();
                            organization.setId(Long.parseLong(xmlEvent.asCharacters().getData().trim()));
                            collectionManager.addIdToListForGenerate(Long.parseLong(xmlEvent.asCharacters().getData()));

                        } else if (startElement.getName().getLocalPart().equals("name")) {
                            xmlEvent = reader.nextEvent();
                            if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                            organization.setName(xmlEvent.asCharacters().getData().trim());

                        } else if (startElement.getName().getLocalPart().equals("coordinates")) {
                            xmlEvent = reader.nextTag();
                            StartElement startElement1 = xmlEvent.asStartElement();

                            if((!listTegs.contains(startElement1.getName().getLocalPart())) || (startElement1.getName().getLocalPart().equals("y"))) throw new XmlTegsWrongValuesException();

                            if(startElement1.getName().getLocalPart().equals("x")){
                                xmlEvent = reader.nextEvent();
                                if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                                float x = Float.parseFloat(xmlEvent.asCharacters().getData().trim());
                                reader.nextTag();
                                xmlEvent = reader.nextTag();
                                StartElement startElement2 = xmlEvent.asStartElement();

                                if((!listTegs.contains(startElement2.getName().getLocalPart())) || (startElement2.getName().getLocalPart().equals("x"))) throw new XmlTegsWrongValuesException();

                                if(startElement2.getName().getLocalPart().equals("y")){
                                    xmlEvent = reader.nextEvent();
                                    if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                                    int y = Integer.parseInt(xmlEvent.asCharacters().getData().trim());
                                    organization.setCoordinates(new Coordinates(x,y));
                                }
                            }
                        } else if (startElement.getName().getLocalPart().equals("creationDate")) {
                            xmlEvent = reader.nextEvent();
                            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                            if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                            if((LocalDate.parse(xmlEvent.asCharacters().getData().trim(), dateFormatter).getYear()>LocalDate.now().getYear())) throw new NotInDeclaredLimitsException();
                            organization.setCreationDate(LocalDate.parse(xmlEvent.asCharacters().getData().trim(), dateFormatter));

                        } else if (startElement.getName().getLocalPart().equals("annualTurnover")) {
                            xmlEvent = reader.nextEvent();
                            if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                            if((Float.parseFloat(xmlEvent.asCharacters().getData().trim()))<0) throw new NotInDeclaredLimitsException();
                            organization.setAnnualTurnover(Float.parseFloat(xmlEvent.asCharacters().getData().trim()));

                        } else if (startElement.getName().getLocalPart().equals("fullName")) {
                            xmlEvent = reader.nextEvent();
                            organization.setFullName(xmlEvent.asCharacters().getData().trim());

                        } else if (startElement.getName().getLocalPart().equals("type")) {
                                xmlEvent = reader.nextEvent();
                                boolean type = false;
                            if (xmlEvent.asCharacters().getData().trim().equals("коммерческий")) {
                                organization.setType(OrganizationType.COMMERCIAL);
                                type = true;
                            }
                            if (xmlEvent.asCharacters().getData().trim().equals("публичный")) {
                                organization.setType(OrganizationType.PUBLIC);
                                type = true;
                            }
                            if (xmlEvent.asCharacters().getData().trim().equals("правительство")) {
                                organization.setType(OrganizationType.GOVERNMENT);
                                type = true;
                            }
                            if (xmlEvent.asCharacters().getData().trim().equals("доверие")) {
                                organization.setType(OrganizationType.TRUST);
                                type = true;
                            }
                            if (xmlEvent.asCharacters().getData().trim().equals("закрытое акционерное общество")) {
                                organization.setType(OrganizationType.PRIVATE_LIMITED_COMPANY);
                                type = true;
                            }
                            if(type == false) throw new NumberFormatException();

                            if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                        } else if (startElement.getName().getLocalPart().equals("officialAddress")) {
                            xmlEvent = reader.nextTag();
                            StartElement startElement1 = xmlEvent.asStartElement();
                            if((!listTegs.contains(startElement1.getName().getLocalPart())) ||
                                    (startElement1.getName().getLocalPart().equals("officialAddress"))) throw new XmlTegsWrongValuesException();
                            if (startElement1.getName().getLocalPart().equals("street")) {
                                xmlEvent = reader.nextEvent();
                                if((xmlEvent.asCharacters().getData().trim()).isEmpty()) throw new MustNotBeEmptyException();
                                if((xmlEvent.asCharacters().getData().trim()).length()>130) throw new NotInDeclaredLimitsException();
                                organization.setOfficialAddress(new Address(xmlEvent.asCharacters().getData().trim()));
                            }
                        }

                    }
                if(xmlEvent.isEndElement()){
                    EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equals("organizations" + count)){
                        resultOrganizations.add(organization);
                        count+=1;
                    }
                }
                }

        }catch(NoSuchElementException exception){
            consoleManager.println("Boot file is empty!");
        }
        catch (XMLStreamException ex){
            consoleManager.println("xml file is corrupt, check it");
            return new Vector<>();
        } catch (XmlTegsWrongValuesException e) {
            consoleManager.println("xml file tags contain invalid field names");
            return new Vector<>();
        }catch (DateTimeParseException ex) {
            consoleManager.println("contains invalid date/dates");
            return new Vector<>();
        }catch(MustNotBeEmptyException ex){
            consoleManager.println("the file contains empty values");
            return new Vector<>();
        }catch(NumberFormatException ex){
            consoleManager.println("The file has been corrupted or it contains/contains incorrect/incorrect format/s of values!");
            return new Vector<>();
        }catch (NotInDeclaredLimitsException ex){
            consoleManager.println("the limit of some values does not meet the requirements");
            return new Vector<>();
        } catch (IOException e) {
            if(Paths.get(fileName).toFile().exists()){
                consoleManager.println("the file cannot be read, check the permission");
            }
            else {
                consoleManager.println("file not found, check the file name");
            }
        }
        return resultOrganizations;

    }
    public LinkedList<Long> getId () {
        return collectionManager.getListForGenerateId();
    }

    public String getFileName () {
        return fileName;
    }
}