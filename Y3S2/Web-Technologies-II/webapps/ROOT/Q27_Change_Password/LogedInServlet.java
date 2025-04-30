package Q27_Change_Password;

import java.io.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/Q27_Change_Password/")
public class LogedInServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Only use existing session

        String loginName = (session != null) ? (String) session.getAttribute("loginName") : null;
        String password = (session != null) ? (String) session.getAttribute("password") : null;

        // If not logged in or credentials are invalid, redirect
        if (loginName == null || password == null || !validateCredentials(loginName, password)) {
            session.removeAttribute("loginName");
            session.removeAttribute("password");
            session.removeAttribute("user");
            response.sendRedirect("/Q24_Login_Account/");
            return;
        }

        // Logged in: continue
        response.sendRedirect("/Q27_Change_Password/index.html");
    }

    private boolean validateCredentials(String loginName, String password) {
        boolean isValid = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
            conn = ds.getConnection();

            String query = "SELECT COUNT(*) AS user_count FROM 1100_users " +
                    "WHERE LOWER(login_name) = LOWER(?) AND password = ?";
            pst = conn.prepareStatement(query);
            pst.setString(1, loginName);
            pst.setString(2, password);
            rs = pst.executeQuery();

            if (rs.next()) {
                isValid = rs.getInt("user_count") > 0;
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pst != null)
                    pst.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValid;
    }
}
