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

@WebServlet("/Q19_Form_Servlet/SearchStudentServlet")
public class SearchStudentServlet extends HttpServlet {
    /*
     * Retrive a list studnets
     * 1. whose name contain the given substring and
     * 2. Who study in the given department
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Query parameters
        String name = request.getParameter("name");
        String dept = request.getParameter("department");

        List<Student> studentList = new ArrayList<>();

        // If both name and dept is empty, return nothing
        if ((name == null || name.trim().isEmpty()) && (dept == null || dept.trim().isEmpty())) {
            request.setAttribute("students", studentList);
            request.getRequestDispatcher("/Q19_Form_Servlet/").forward(request, response);
            return;
        }

        // If 'name' or 'dept' is null or empty, set it to an empty string
        name = (name == null || name.trim().isEmpty()) ? "" : name;
        dept = (dept == null || dept.trim().isEmpty()) ? "" : dept;

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
            String query;
            if (name != null && !name.isEmpty()) {
                query = "SELECT s.roll AS roll_number, s.name AS student_name, d.name AS dept_name " +
                        "FROM students AS s " +
                        "JOIN departments AS d ON s.deptID = d.id " +
                        "WHERE LOWER(s.name) LIKE LOWER(?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, "%" + name + "%");
            } else if (dept != null && !dept.isEmpty()) {
                query = "SELECT s.roll AS roll_number, s.name AS student_name, d.name AS dept_name " +
                        "FROM students AS s " +
                        "JOIN departments AS d ON s.deptID = d.id " +
                        "WHERE LOWER(d.name) = LOWER(?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, dept);
            } else {
                throw new IllegalArgumentException("Either name or department must be provided");
            }

            rs = pst.executeQuery();

            while (rs.next()) {
                int rollNumber = rs.getInt("roll_number");
                String studentName = rs.getString("student_name");
                String deptName = rs.getString("dept_name");

                studentList.add(new Student(rollNumber, studentName, deptName));
            }
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

        /*
         * forward the results to GetDepartmentServlet which is directory-mapped
         */
        request.setAttribute("students", studentList);
        request.getRequestDispatcher("/Q19_Form_Servlet/").forward(request, response);
    }
}
