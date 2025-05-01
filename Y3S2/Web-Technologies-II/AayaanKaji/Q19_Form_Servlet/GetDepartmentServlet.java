package Q19_Form_Servlet;

import java.io.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

import java.sql.*;
import java.util.*;

@WebServlet("/Q19_Form_Servlet/")
public class GetDepartmentServlet extends HttpServlet {
    /*
     * Populates the departments drop down list
     * Sets studentsList to studnets attribute if part of servet chaining
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*
         * Typecast the attribute without checking type safty, supress the warnings
         */
        @SuppressWarnings("unchecked")
        List<Student> studentList = (List<Student>) request.getAttribute("students");
        if (studentList == null) {
            studentList = new ArrayList<>();
        }

        List<String> deptList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        PrintWriter out = response.getWriter();

        try {
            // Look up the DataSource using JNDI
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
            conn = ds.getConnection();

            // query to find studnets by name with given substring
            String query = "SELECT name FROM departments;";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                deptList.add(rs.getString("name"));
            }
            conn.close();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
            } catch (SQLException e) {
                out.println("Error closing resources: " + e.getMessage());
            }
        }

        // forward the results
        request.setAttribute("students", studentList);
        request.setAttribute("departments", deptList);
        request.getRequestDispatcher("/Q19_Form_Servlet/index.jsp").forward(request, response);
    }
}
