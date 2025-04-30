package Database;

import java.io.*;
import java.sql.*;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/Database/CreateTablesServlet")
public class CreateTablesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        Statement stmt = null;

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");

            conn = ds.getConnection();
            stmt = conn.createStatement();

            // Create 1100_departments table
            String createDepartmentsTable = "CREATE TABLE IF NOT EXISTS 1100_departments ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(50) UNIQUE"
                    + ")";
            stmt.executeUpdate(createDepartmentsTable);

            // Create 1100_users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS 1100_users ("
                    + "login_name VARCHAR(50) PRIMARY KEY,"
                    + "password VARCHAR(16) NOT NULL,"
                    + "email VARCHAR(100) DEFAULT NULL,"
                    + "first_name VARCHAR(50) DEFAULT NULL,"
                    + "last_name VARCHAR(50) DEFAULT NULL,"
                    + "phone VARCHAR(10) DEFAULT NULL"
                    + ")";
            stmt.executeUpdate(createUsersTable);

            // Create 1100_students table
            String createStudentsTable = "CREATE TABLE IF NOT EXISTS 1100_students ("
                    + "roll INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(30) NOT NULL,"
                    + "deptID INT,"
                    + "FOREIGN KEY (deptID) REFERENCES 1100_departments(id)"
                    + ")";
            stmt.executeUpdate(createStudentsTable);

            // Create the states table
            String createStatesTable = "CREATE TABLE IF NOT EXISTS 1100_states ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL UNIQUE"
                    + ")";
            stmt.executeUpdate(createStatesTable);

            // Create the districts table
            String createDistrictsTable = "CREATE TABLE IF NOT EXISTS 1100_districts ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(100) NOT NULL UNIQUE,"
                    + "description TEXT DEFAULT NULL,"
                    + "state_id INT,"
                    + "FOREIGN KEY (state_id) REFERENCES 1100_states(id)"
                    + ")";
            stmt.executeUpdate(createDistrictsTable);

            // Create the questions table
            String createQuestionsTable = "CREATE TABLE IF NOT EXISTS 1100_questions ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "question_text TEXT NOT NULL, "
                    + "optionA VARCHAR(255) NOT NULL, "
                    + "optionB VARCHAR(255) NOT NULL, "
                    + "optionC VARCHAR(255) NOT NULL, "
                    + "optionD VARCHAR(255) NOT NULL, "
                    + "answer CHAR(1) NOT NULL CHECK (answer IN ('A', 'B', 'C', 'D'))"
                    + ")";
            stmt.executeUpdate(createQuestionsTable);

            // PC Components Table
            String createComponentsTable = "CREATE TABLE IF NOT EXISTS 1100_components ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "component_type VARCHAR(50) NOT NULL, "
                    + "manufacturer VARCHAR(100) NOT NULL, "
                    + "model VARCHAR(100) NOT NULL UNIQUE, "
                    + "price DECIMAL(10, 2) NOT NULL"
                    + ")";
            stmt.executeUpdate(createComponentsTable);

            // Subjects Table
            String createSubjectsTable = "CREATE TABLE IF NOT EXISTS 1100_subjects ("
                    + "subject_code VARCHAR(20) PRIMARY KEY, "
                    + "subject_name VARCHAR(100) NOT NULL UNIQUE"
                    + ")";
            stmt.executeUpdate(createSubjectsTable);

            // Semesters Table
            String createSemestersTable = "CREATE TABLE IF NOT EXISTS 1100_semesters ("
                    + "semester_id INT PRIMARY KEY, "
                    + "semester_name VARCHAR(50) NOT NULL UNIQUE"
                    + ")";
            stmt.executeUpdate(createSemestersTable);

            // Marks Table
            String createMarksTable = "CREATE TABLE IF NOT EXISTS 1100_marks ("
                    + "id INT AUTO_INCREMENT UNIQUE, "
                    + "roll INT NOT NULL, "
                    + "subject_code VARCHAR(20) NOT NULL, "
                    + "semester_id INT NOT NULL, "
                    + "marks INT CHECK (marks BETWEEN 0 AND 100), "
                    + "PRIMARY KEY (roll, subject_code, semester_id), "
                    + "FOREIGN KEY (roll) REFERENCES 1100_students(roll), "
                    + "FOREIGN KEY (subject_code) REFERENCES 1100_subjects(subject_code), "
                    + "FOREIGN KEY (semester_id) REFERENCES 1100_semesters(semester_id)"
                    + ")";
            stmt.executeUpdate(createMarksTable);

            out.println("<h3>All tables created successfully.</h3>");

            response.sendRedirect("/Database/ShowTablesServlet");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
