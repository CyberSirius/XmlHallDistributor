package bg.rashev.xml.distributor.enums;

/**
 * Created by CyberSirius on 26-Jan-16.
 */
public enum Constants {
    CONFIG_FILE_PATH("resources\\config\\config.properties"),
    DEFAULT_PROTOCOL_PATH("resources\\xml\\protocols\\Protocol.xml"),
    DEFAULT_OUTPUT_DIRECTORY("resources\\xml\\generatedDocuments"),
    STUDENT_SCHEMA_PATH("resources\\xml\\schemas\\StudentPassSchema.xsd"),
    PROTOCOL_SCHEMA_PATH("resources\\xml\\schemas\\ProtocolSchema.xsd");

    private String string;

    Constants(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
