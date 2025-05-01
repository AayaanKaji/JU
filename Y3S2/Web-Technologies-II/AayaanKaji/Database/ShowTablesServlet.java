package Database;

import utils.HtmlUtil;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;

@WebServlet("/Database/ShowTablesServlet")
public class ShowTablesServlet extends HttpServlet {
    // Max numbers of tuples/rows to be printed per table
    private int maxRowCount = 50;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ResultSet dataRs = null;
        PrintWriter out = response.getWriter();

        try {
            // Look up the DataSource using JNDI
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");

            conn = ds.getConnection();

            // Query the database
            String query = "SHOW TABLES";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            out.println("<html><body>");
            out.println("<h2>Tables inside the 'test' Database:</h2>");

            while (rs.next()) {
                String colName = rs.getString(1);
                if (colName.length() < 4 || !colName.startsWith("1100")) {
                    continue;
                }

                out.println("<h3>Table: " + HtmlUtil.escapeHtml(colName) + "</h3>");

                query = "SELECT * FROM `" + colName + "`";
                stmt = conn.prepareStatement(query);
                dataRs = stmt.executeQuery();

                // Prints table body
                printTableBody(dataRs, out);
            }

            out.println("</body></html>");
        } catch (SQLException | NamingException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                rs.close();
                dataRs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                out.println("Error closing resources: " + e.getMessage());
            }
        }
    }


    // Prints a Table Body, i.e. Rows
    private void printTableBody(ResultSet dataRs, PrintWriter out) throws SQLException {
        ResultSetMetaData metaData = dataRs.getMetaData();
        int colCount = metaData.getColumnCount();

        out.println("<table border='1' cellpadding='5' cellspacing='0'>");

        // Table Header
        out.println("<thead><tr>");
        for (int i = 1; i <= colCount; i++) {
            out.println("<th>" + HtmlUtil.escapeHtml(metaData.getColumnName(i)) + "</th>");
        }
        out.println("</tr></thead>");

        // Table Body
        out.println("<tbody>");
        int rowCount = 0;
        while (dataRs.next() && rowCount++ < maxRowCount) {
            out.println("<tr>");
            for (int i = 1; i <= colCount; i++) {
                String value = dataRs.getString(i);
                if (value == null)
                    value = "";
                out.println("<td>" + HtmlUtil.escapeHtml(value) + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</tbody>");

        out.println("</table><br>");
    }
}
