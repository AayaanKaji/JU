package Q32_Dashboard;

import java.io.*;

import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

import Database.User;

import javax.servlet.annotation.*;

import java.sql.*;
import java.util.*;

@WebServlet("/Q32_Dashboard/")
public class UserDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check session
        HttpSession session = request.getSession(false);
        String loginName = (session != null) ? (String) session.getAttribute("loginName") : null;
        String password = (session != null) ? (String) session.getAttribute("password") : null;

        if (loginName == null || password == null) {
            // redirect to login page
            response.sendRedirect("/AayaanKaji/Q24_Login_Account/");
            return;
        }

        // Init
        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {

            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
            conn = ds.getConnection();

            stmt = conn.prepareStatement(
                    "SELECT login_name, password, email, first_name, last_name, phone FROM users where LOWER(login_name) = LOWER(?) AND password = ?");
            stmt.setString(1, loginName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            while (rs.next()) {
                user = new User(
                        rs.getString("login_name"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone"));
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            out.println("Error: " + e);
        } finally {
            try {
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Q32_Dashboard/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
