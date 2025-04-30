package Q22_Create_Account;

import java.io.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;

import java.sql.*;
import java.util.*;

@WebServlet("/Q22_Create_Account/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");

        if (loginName == null || password == null || loginName.trim().isEmpty() || password.trim().isEmpty()) {
            response.getWriter().write("failure: Fill required details.");
            return;
        }

        loginName = loginName.trim().toLowerCase();
        password = password.trim();
        email = (email == null) ? "" : email.trim();
        firstName = (firstName == null) ? "" : firstName.trim();
        lastName = (lastName == null) ? "" : lastName.trim();
        phone = (phone == null) ? "" : phone.trim();

        boolean isInserted = insertAccountIntoDatabase(loginName, password, email, firstName, lastName, phone);
        if (isInserted) {
            response.getWriter().write("success: Account created successfully!");

            // Store in session
            HttpSession session = request.getSession(true);
            session.setAttribute("loginName", loginName);
            session.setAttribute("password", password);
            session.setMaxInactiveInterval(30 * 60); // 30 minutes
        } else {
            response.getWriter().write("failure: Something went wrong.");
        }
    }

    private boolean insertAccountIntoDatabase(String loginName, String password, String email, String firstName,
            String lastName, String phone) {

        Connection conn = null;
        PreparedStatement pst = null;

        try {
            // Look up the DataSource using JNDI
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
            conn = ds.getConnection();

            String query = "INSERT INTO 1100_users (login_name, password, email, first_name, last_name, phone) VALUES (?, ?, ?, ?, ?, ?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, loginName);
            pst.setString(2, password);

            // If these fields are null or empty, set them as NULL in the database
            if (email.isEmpty()) {
                pst.setNull(3, Types.VARCHAR);
            } else {
                pst.setString(3, email);
            }
            if (firstName.isEmpty()) {
                pst.setNull(4, Types.VARCHAR);
            } else {
                pst.setString(4, firstName);
            }

            if (lastName.isEmpty()) {
                pst.setNull(5, Types.VARCHAR);
            } else {
                pst.setString(5, lastName);
            }

            if (phone.isEmpty()) {
                pst.setNull(6, Types.VARCHAR);
            } else {
                pst.setString(6, phone);
            }

            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
