package bg.rashev.xml.distributor;

import bg.rashev.xml.distributor.enums.Constants;
import bg.rashev.xml.distributor.model.Protocol;
import bg.rashev.xml.distributor.model.StudentPass;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

class ReadWriteUtils {
    private static Marshaller marshaller = null;
    private static Unmarshaller unmarshaller = null;
    private Class<?> type;
    private File schemaFile;

    public ReadWriteUtils(Class<?> type) throws JAXBException, SAXException {
        this.type = type;
        setSchemaFile();
        marshaller = getMarshaller();
        unmarshaller = getUnmarshaller();
    }

    public void setType(Class<?> type) throws JAXBException, SAXException {
        this.type = type;
        setSchemaFile();
        marshaller = getMarshaller();
        unmarshaller = getUnmarshaller();
    }

    private void setSchemaFile() {
        if (type == StudentPass.class) schemaFile = new File(Constants.STUDENT_SCHEMA_PATH.toString());
        else if (type == Protocol.class)
            schemaFile = new File(Constants.PROTOCOL_SCHEMA_PATH.toString());
        else throw new IllegalArgumentException("Wrong type!");
    }

    public void writeXml(final Object object, final File file) throws JAXBException {
        marshaller.marshal(object, file);
    }

    public void writeXml(final Object object, final OutputStream outputStream) throws JAXBException {
        marshaller.marshal(object, outputStream);
    }

    public Object readFromXml(final File file) throws JAXBException {
        ValidationEventCollector validationEventCollector = new ValidationEventCollector();
        unmarshaller.setEventHandler(validationEventCollector);
        Object object = unmarshaller.unmarshal(file);
        boolean errorFlag = false;
        for (ValidationEvent validationEvent : validationEventCollector.getEvents()) {
            System.out.println(validationEvent.getMessage());
            errorFlag = true;
        }
        if (errorFlag)
            throw new IllegalArgumentException("Protocol is not in the correct format!");
        return object;
    }

    private Marshaller getMarshaller() throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.toString());
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        marshaller.setSchema(schema);
        return marshaller;
    }

    private Unmarshaller getUnmarshaller() throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(type);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(schemaFile);
        unmarshaller.setSchema(schema);
        return unmarshaller;
    }
}