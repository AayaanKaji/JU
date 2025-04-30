package Database;

import utils.HtmlUtil;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;

@WebServlet("/Database/DropDataServlet")
public class DropDataServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
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

            // Before truncating, disable foreign key checks
            PreparedStatement disableFK = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0;");
            disableFK.executeUpdate();

            // Truncate
            while (rs.next()) {
                String colName = rs.getString(1);
                if (colName.length() < 4 || !colName.startsWith("1100")) {
                    continue;
                }

                query = "TRUNCATE TABLE `" + colName + "`";
                stmt = conn.prepareStatement(query);
                stmt.executeUpdate();
            }

            // After truncating, re-enable foreign key checks
            PreparedStatement enableFK = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1;");
            enableFK.executeUpdate();

            response.sendRedirect("/Database/ShowTablesServlet");
        } catch (SQLException | NamingException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
