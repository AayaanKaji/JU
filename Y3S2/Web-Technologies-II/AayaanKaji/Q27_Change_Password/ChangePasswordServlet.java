package Q27_Change_Password;

import java.io.*;
import javax.naming.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet("/Q27_Change_Password/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        PrintWriter out = response.getWriter();
        Connection conn = null;
        PreparedStatement pstSelect = null;
        PreparedStatement pstUpdate = null;
        ResultSet rs = null;

        HttpSession session = request.getSession(false);
        String loginName = (session != null) ? (String) session.getAttribute("loginName") : null;
        String oldPassword = request.getParameter("oldPassword").trim();
        String newPassword = request.getParameter("newPassword").trim();

        if (loginName == null || oldPassword == null || newPassword == null) {
            out.println("Error: Missing parameters.");
            return;
        }

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
            conn = ds.getConnection();

            // Step 1: Validate old password
            String selectSql = "SELECT password FROM users WHERE LOWER(login_name) = LOWER(?)";
            pstSelect = conn.prepareStatement(selectSql);
            pstSelect.setString(1, loginName);
            rs = pstSelect.executeQuery();

            if (!rs.next()) {
                out.println("Error: User not found.");
                return;
            }

            String currentPassword = rs.getString("password");

            if (!currentPassword.equals(oldPassword)) {
                out.println("Error: Old password is incorrect.");
                return;
            }

            if (currentPassword.equals(newPassword)) {
                out.println("Error: New password must be different from old password.");
                return;
            }

            // Step 2: Update password
            String updateSql = "UPDATE users SET password = ? WHERE LOWER(login_name) = LOWER(?)";
            pstUpdate = conn.prepareStatement(updateSql);
            pstUpdate.setString(1, newPassword);
            pstUpdate.setString(2, loginName);

            int rowsUpdated = pstUpdate.executeUpdate();

            if (rowsUpdated > 0) {
                out.println("Password updated successfully.");
                session.setAttribute("password", newPassword);
            } else {
                out.println("Error: Failed to update password.");
            }

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstSelect != null)
                    pstSelect.close();
                if (pstUpdate != null)
                    pstUpdate.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
