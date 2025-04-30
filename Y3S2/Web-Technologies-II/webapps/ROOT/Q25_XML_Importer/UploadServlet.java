package Q25_XML_Importer;

import java.io.*;
import java.nio.file.Paths;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/Q25_XML_Importer/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Part filePart = request.getPart("xmlfile");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File file = new File(getServletContext().getRealPath("/assets/") + fileName);
            filePart.write(file.getAbsolutePath());

            QuestionXMLImporter importer = new QuestionXMLImporter(file.getAbsolutePath());
            importer.insert();

            response.getWriter().println("Questions inserted successfully.");
            response.sendRedirect("/");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
