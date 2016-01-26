package bg.rashev.xml.distributor.enums;

/**
 * Created by CyberSirius on 26-Jan-16.
 */
public enum Constants {
    CONFIG_FILE_PATH("resources\\config\\config.properties"),
    DEFAULT_PROTOCOL_PATH("resources\\xml\\protocols\\Protocol.xml"),
    DEFAULT_XML_OUTPUT_DIRECTORY("resources\\xml\\generatedDocuments\\xml"),
    STUDENT_SCHEMA_PATH("resources\\xml\\schemas\\StudentPassSchema.xsd"),
    PROTOCOL_SCHEMA_PATH("resources\\xml\\schemas\\ProtocolSchema.xsd"),
    STUDENT_STYLE("resources\\xml\\styles\\student_style.xsl"),
    PROTOCOL_STYLE("resources\\xml\\styles\\protocol_style.xsl"),
    DEFAULT_HTML_OUTPUT_DIRECTORY("resources\\xml\\generatedDocuments\\html");

    private String string;

    Constants(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
