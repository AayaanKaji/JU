package Q29_Login_Auth;

import java.io.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;

import java.sql.*;
import java.util.*;

@WebServlet("/Q29_Login_Auth/LoginAuthenticateServlet")
public class LoginAuthenticateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();

        try {
            // Look up the DataSource using JNDI
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
            conn = ds.getConnection();

            String sql = "SELECT password FROM users WHERE LOWER(login_name) = LOWER(?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, loginName);
            rs = pst.executeQuery();

            // Check if the user exists
            if (rs.next()) {
                String storedPassword = rs.getString("password");

                if (password.equals(storedPassword)) {
                    out.println("Login Successful!");

                    // Store in session
                    HttpSession session = request.getSession(true);
                    session.setAttribute("loginName", loginName);
                    session.setAttribute("password", password);
                    session.setMaxInactiveInterval(30 * 60); // 30 minutes
                } else {
                    out.println("Error: Incorrect password.");
                }
            } else {
                out.println("Error: User not found. Please register first.");
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            out.println("Error: " + e);
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
