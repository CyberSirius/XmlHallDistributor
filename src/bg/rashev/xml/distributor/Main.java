package bg.rashev.xml.distributor;

import bg.rashev.xml.distributor.enums.Constants;
import bg.rashev.xml.distributor.model.Protocol;
import bg.rashev.xml.distributor.model.StudentPass;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    //private static final String CONFIG_FILE_PATH = "resources\\config\\config.properties";
    private static final Properties properties = new Properties();

    public static void main(String[] args) throws JAXBException, SAXException, IOException, TransformerException, ParserConfigurationException {
        switch (args[0]) {
            case "set":
                switch (args[1]) {
                    case "protocol":
                        setProtocol(args[2]);
                        break;
                    case "output":
                        setOutput(args[2]);
                        break;
                    case "default":
                        setProtocol(Constants.DEFAULT_PROTOCOL_PATH.toString());
                        setOutput(Constants.DEFAULT_XML_OUTPUT_DIRECTORY.toString());
                        break;
                    default:
                        throw new IllegalArgumentException("Command not found!");
                }
                break;
            case "get":
                switch (args[1]) {
                    case "student":
                        getStudentPass(args[2]);
                        break;
                    case "protocol":
                        getProtocol();
                        break;
                    default:
                        throw new IllegalArgumentException("Command not found!");
                }
                break;
            case "help":
                System.out.println("set protocol <path_name>: sets the current protocol as the one, found at <path_name>");
                System.out.println("set output <path_name>: sets the current output file location for a student's pass at <path_name>");
                System.out.println("get <faculty_number>: retrieves the student's pass for a student with a faculty number <faculty_number> and generates XML document at the given output location.");
                System.out.println("java -jar HallDistributor.jar <command> will execute the <command> command.");
                break;
            default:
                throw new IllegalArgumentException("Command not found!");
        }

    }

    private static void getProtocol() throws IOException, JAXBException, SAXException, ParserConfigurationException, TransformerException {
        InputStream inputStream = new FileInputStream(Constants.CONFIG_FILE_PATH.toString());
        properties.load(inputStream);
        File protocolFile = new File(properties.getProperty("protocol"));
        ReadWriteUtils utils = new ReadWriteUtils(Protocol.class);
        utils.writeXml(utils.readFromXml(protocolFile), System.out);
        System.out.println("Do you want to open protocol with your default viewing program?");
        if (awaitResponse())
            Desktop.getDesktop().open(protocolFile);
        System.out.println("Do you want to transform and open the protocol file with your default viewing program?");
        if (awaitResponse())
            Desktop.getDesktop().open(transformXML(protocolFile, new File(Constants.PROTOCOL_STYLE.toString())));
    }

    private static void getStudentPass(String facultyNumber) throws IOException, JAXBException, SAXException, TransformerException, ParserConfigurationException {
        if (Pattern.matches("\\d{5,6}", facultyNumber)) {
            InputStream inputStream = new FileInputStream(Constants.CONFIG_FILE_PATH.toString());
            properties.load(inputStream);
            File protocolFile = new File(properties.getProperty("protocol"));
            ReadWriteUtils utils = new ReadWriteUtils(Protocol.class);
            Protocol protocol = (Protocol) utils.readFromXml(protocolFile);
            StudentPass studentPass = protocol.getStudentPass(Integer.parseInt(facultyNumber));
            utils.setType(StudentPass.class);
            File outputFile = new File(properties.getProperty("output") + "\\StudentPass" + facultyNumber + ".xml");
            if (outputFile.createNewFile())
                System.out.println("File created!");
            utils.writeXml(studentPass, outputFile);
            utils.writeXml(studentPass, System.out);
            System.out.println("Do you want to open the generated XML Document with your default viewing program?");
            if (awaitResponse())
                Desktop.getDesktop().open(outputFile);
            System.out.println("Do you want to transform the generated XML Document to html file and open with your default viewing program?");
            if (awaitResponse()) {
                Desktop.getDesktop().open(transformXML(outputFile, new File(Constants.STUDENT_STYLE.toString())));
            }
            inputStream.close();
        } else throw new IllegalArgumentException("Invalid faculty number is entered!");
    }

    private static boolean awaitResponse() throws IOException {
        System.out.println("yes/no?");
        Scanner in = new Scanner(System.in);
        while (true) {
            switch (in.nextLine()) {
                case "yes":
                    return true;
                case "no":
                    return false;
                default:
                    System.out.println("Invalid response, try again!");
            }
        }
    }

    private static void setOutput(String outputFilePath) throws IOException {
        File file = new File(outputFilePath);
        if (file.createNewFile())
            System.out.println("File created!");
        OutputStream outputStream = new FileOutputStream(Constants.CONFIG_FILE_PATH.toString());
        properties.setProperty("output", outputFilePath);
        properties.store(outputStream, null);
        outputStream.close();
    }

    private static void setProtocol(String protocolFilePath) throws IOException {
        File file = new File(protocolFilePath);
        if (!file.exists())
            throw new FileNotFoundException("Protocol file was not found!");
        OutputStream outputStream = new FileOutputStream(Constants.CONFIG_FILE_PATH.toString());
        properties.setProperty("protocol", protocolFilePath);
        properties.store(outputStream, null);
        outputStream.close();
    }

    public static File transformXML(File xmlFile, File xsltFile) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        StreamSource styleSource = new StreamSource(xsltFile);
        Transformer transformer = TransformerFactory.newInstance().newTransformer(styleSource);
        DOMSource source = new DOMSource(document);
        File htmlFile = new File(Constants.DEFAULT_HTML_OUTPUT_DIRECTORY.toString() + "\\" + xmlFile.getName().replaceFirst("[.][^.]+$", "") + ".html");
        StreamResult result = new StreamResult(new FileOutputStream(htmlFile));
        transformer.setOutputProperty(OutputKeys.METHOD, "html");
        transformer.transform(source, result);
        return htmlFile;
    }
}