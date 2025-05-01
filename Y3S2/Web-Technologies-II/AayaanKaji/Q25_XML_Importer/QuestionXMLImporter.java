package Q25_XML_Importer;

import java.io.File;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class QuestionXMLImporter {
    private File xmlFile;

    public QuestionXMLImporter(String filePath) {
        this.xmlFile = new File(filePath);
    }

    public void insert() throws Exception {
        // Get database connection via JNDI
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
        Connection conn = ds.getConnection();

        // Parse XML
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        NodeList list = doc.getElementsByTagName("question");

        String sql = "INSERT INTO questions (question_text, optionA, optionB, optionC, optionD, answer) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < list.getLength(); i++) {
            Element q = (Element) list.item(i);
            ps.setString(1, q.getElementsByTagName("text").item(0).getTextContent());
            ps.setString(2, q.getElementsByTagName("optionA").item(0).getTextContent());
            ps.setString(3, q.getElementsByTagName("optionB").item(0).getTextContent());
            ps.setString(4, q.getElementsByTagName("optionC").item(0).getTextContent());
            ps.setString(5, q.getElementsByTagName("optionD").item(0).getTextContent());
            ps.setString(6, q.getElementsByTagName("answer").item(0).getTextContent());
            ps.executeUpdate();
        }

        ps.close();
        conn.close();
    }
}

