package bg.rashev.xml.distributor;

import bg.rashev.xml.distributor.enums.Constants;
import bg.rashev.xml.distributor.model.Protocol;
import bg.rashev.xml.distributor.model.StudentPass;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    //private static final String CONFIG_FILE_PATH = "resources\\config\\config.properties";
    private static final Properties properties = new Properties();

    public static void main(String[] args) throws JAXBException, SAXException, IOException {
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
                        setOutput(Constants.DEFAULT_OUTPUT_DIRECTORY.toString());
                        break;
                    default:
                        throw new IllegalArgumentException("Command not found!");
                }
                break;
            case "get":
                getStudentPass(args[1]);
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

    private static void getStudentPass(String facultyNumber) throws IOException, JAXBException, SAXException {
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
            System.out.println("yes/no?");
            Scanner in = new Scanner(System.in);
            boolean flag = true;
            while (flag) {
                switch (in.nextLine()) {
                    case "yes":
                        Desktop.getDesktop().open(outputFile);
                        flag = false;
                        break;
                    case "no":
                        flag = false;
                        break;
                    default:
                        System.out.println("Invalid response, try again!");
                }
            }
            inputStream.close();
        } else throw new IllegalArgumentException("Invalid faculty number is entered!");

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
}