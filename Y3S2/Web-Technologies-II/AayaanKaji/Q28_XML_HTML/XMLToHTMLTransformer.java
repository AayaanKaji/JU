package Q28_XML_HTML;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class XMLToHTMLTransformer {

    public static void main(String[] args) {
        try {
            // Load XML and XSLT files
            File xmlFile = new File("assets/questions.xml");
            File xsltFile = new File("Q28_XML_HTML/transform.xsl");

            // Set up the transformer factory
            TransformerFactory factory = TransformerFactory.newInstance();
            StreamSource xslt = new StreamSource(xsltFile);
            Transformer transformer = factory.newTransformer(xslt);

            // Set up the XML source
            StreamSource xml = new StreamSource(xmlFile);

            // Output the result to an HTML file
            File outputFile = new File("Q28_XML_HTML/quiz.html");
            StreamResult result = new StreamResult(outputFile);
            
            // Perform the transformation
            transformer.transform(xml, result);

            System.out.println("Transformation successful. HTML output saved to " + outputFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

