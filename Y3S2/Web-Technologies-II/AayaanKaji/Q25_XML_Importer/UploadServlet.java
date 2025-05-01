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
            String submittedFileName = filePart.getSubmittedFileName();
            String fileName = Paths.get(submittedFileName).getFileName().toString(); // safe extraction

            File uploadDir = new File(getServletContext().getRealPath("/assets/"));
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File targetFile = new File(uploadDir, fileName);

            filePart.write(targetFile.getAbsolutePath());

            QuestionXMLImporter importer = new QuestionXMLImporter(targetFile.getAbsolutePath());
            importer.insert();

            response.getWriter().println("Questions inserted successfully.");
            response.sendRedirect("/AayaanKaji/");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
