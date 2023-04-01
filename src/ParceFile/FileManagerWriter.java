package ParceFile;

import Utility.CollectionManager;
import Utility.ConsoleManager;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManagerWriter {
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private FileManagerReader fileManagerReader;

    public FileManagerWriter(CollectionManager collectionManager, FileManagerReader fileManagerReader) {
        this.collectionManager = collectionManager;
        this.consoleManager = new ConsoleManager();
        this.fileManagerReader = fileManagerReader;
    }

    private void writerInFile(OutputStream outputStream) throws  XMLStreamException {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = outputFactory.createXMLStreamWriter(outputStream);

        xmlStreamWriter.writeStartDocument("1.0");
        xmlStreamWriter.writeStartElement("organizations");
        int size = collectionManager.getCollectionSize();
        int count = 0;
        while (size > 0) {
            xmlStreamWriter.writeStartElement("organizations" + count);

            xmlStreamWriter.writeStartElement("id");
            xmlStreamWriter.writeCharacters(String.valueOf((collectionManager.getCollectinVector().get(count).getId())));
            xmlStreamWriter.writeEndElement();


            xmlStreamWriter.writeStartElement("name");
            xmlStreamWriter.writeCharacters(collectionManager.getCollectinVector().get(count).getName());
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("coordinates");
            xmlStreamWriter.writeStartElement("x");
            xmlStreamWriter.writeCharacters(String.valueOf((collectionManager.getCollectinVector().get(count).getCoordinates().getX())));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("y");
            xmlStreamWriter.writeCharacters(String.valueOf((collectionManager.getCollectinVector().get(count).getCoordinates().getY())));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("creationDate");
            xmlStreamWriter.writeCharacters(String.valueOf((collectionManager.getCollectinVector().get(count).getCreationDate())));
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("annualTurnover");
            xmlStreamWriter.writeCharacters(String.valueOf((collectionManager.getCollectinVector().get(count).getAnnualTurnover())));
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("fullName");
            xmlStreamWriter.writeCharacters(collectionManager.getCollectinVector().get(count).getFullName());
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("type");
            xmlStreamWriter.writeCharacters(String.valueOf((collectionManager.getCollectinVector().get(count).getType())));
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeStartElement("officialAddress");
            xmlStreamWriter.writeStartElement("street");
            xmlStreamWriter.writeCharacters(collectionManager.getCollectinVector().get(count).getOfficialAddress().getStreet());
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement();

            size -= 1;
            count += 1;
        }

        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
        xmlStreamWriter.close();

    }

        public void writePrettyInXml() throws XMLStreamException, IOException, TransformerException {

            String fileName = fileManagerReader.getFileName();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            writerInFile(out);
            String xml = new String(out.toByteArray());
            String prettyPrintXML = formatXML(xml);
            Path paths = Paths.get(fileName);
            try {
                if(!paths.toFile().exists()) {
                    consoleManager.println("file not found, suggest to create a new file");
                    consoleManager.print("enter a name for the new file: ");
                    String newFileName = consoleManager.readString().trim()+".xml";
                    paths = Paths.get(newFileName);
                }
                Files.write(paths, prettyPrintXML.getBytes());
                consoleManager.println("collection successfully saved");

            }catch (IOException ex){
                if(paths.toFile().exists()) {
                    consoleManager.println("unable to write data to the file, most likely you do not have write access to the file");
                }
            }

        }

        private static String formatXML(String xml) throws TransformerException {


            TransformerFactory transformerFactory = TransformerFactory.newInstance();

            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE,"yes");
            StreamSource source = new StreamSource(new StringReader(xml));
            StringWriter output = new StringWriter();
            transformer.transform(source, new StreamResult(output));

            return output.toString();



    }
}