import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DTD_validator {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("question_paper.xml");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setValidating(true);

            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler dh = new DefaultHandler();

            saxParser.parse(xmlFile, dh);
            System.out.println("XML is valid with DTD!");
        } catch (SAXException e) {
            System.out.println("XML is not valid: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}