package Q22_Create_Account;

import java.io.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;

import java.sql.*;
import java.util.*;

@WebServlet("/Q22_Create_Account/CheckLoginNameServlet")
public class CheckLoginNameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String loginName = request.getParameter("loginName");

        if (loginName == null || loginName.trim().isEmpty()) {
            response.getWriter().write("LoginName not valid.");
            return;
        }

        if (checkLoginNameInDatabase(loginName)) {
            response.getWriter().write("available");
        } else {
            response.getWriter().write("unavailable");
        }
    }

    private boolean checkLoginNameInDatabase(String loginName) {
        int row_count = -1;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Look up the DataSource using JNDI
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
            conn = ds.getConnection();

            // check is the given name is available or not
            String query = "SELECT count(login_name) AS row_count " +
                    "FROM 1100_users " +
                    "WHERE LOWER(login_name) = LOWER(?);";

            pst = conn.prepareStatement(query);
            pst.setString(1, loginName);
            rs = pst.executeQuery();

            while (rs.next()) {
                row_count = rs.getInt("row_count");
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return row_count == 0;
    }
}
