package Q11_DTD_Validator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DTD_Validator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java DTD_Validator <xml file>");
            System.exit(1);
        }

        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setValidating(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();

            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    System.err.println("Error: " + exception.getMessage());
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.err.println("Fetal Error: " + exception.getMessage());
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    System.err.println("Warning: " + exception.getMessage());
                }
            });

            builder.parse(args[0]);

            System.out.println("XML is valid with DTD!");
        } catch (SAXException e) {
            System.out.println("XML is not valid!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}