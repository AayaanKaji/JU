package Database;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.naming.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

@WebServlet("/Database/InsertDataServlet")
public class InsertDataServlet extends HttpServlet {

    // --- Classes ---
    static class Component {
        String type;
        String manufacturer;
        String model;
        double price;

        Component(String type, String manufacturer, String model, double price) {
            this.type = type;
            this.manufacturer = manufacturer;
            this.model = model;
            this.price = price;
        }
    }

    static class Department {
        String name;

        Department(String name) {
            this.name = name;
        }
    }

    static class District {
        String name;
        String description;
        String stateName;

        District(String name, String description, String stateName) {
            this.name = name;
            this.description = description;
            this.stateName = stateName;
        }
    }

    static class User {
        String loginName, password, email, firstName, lastName, phone;

        User(String loginName, String password, String email, String firstName, String lastName, String phone) {
            this.loginName = loginName;
            this.password = password;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
        }
    }

    static class State {
        String name;

        State(String name) {
            this.name = name;
        }
    }

    static class Student {
        String name;
        int deptID;

        Student(String name, int deptID) {
            this.name = name;
            this.deptID = deptID;
        }
    }

    static class Subject {
        String subjectCode;
        String subjectName;

        Subject(String subjectCode, String subjectName) {
            this.subjectCode = subjectCode;
            this.subjectName = subjectName;
        }
    }

    static class Semester {
        int semesterId;
        String semesterName;

        Semester(int semesterId, String semesterName) {
            this.semesterId = semesterId;
            this.semesterName = semesterName;
        }
    }

    static class Marks {
        String roll;
        String subjectCode;
        int semesterId;
        int marks;

        Marks(String roll, String subjectCode, int semesterId, int marks) {
            this.roll = roll;
            this.subjectCode = subjectCode;
            this.semesterId = semesterId;
            this.marks = marks;
        }
    }

    // ---------------------

    private static final Random random = new Random();
    private static final String[] firstNames = { "Alice", "Bob", "Carol", "David", "Eve" };
    private static final String[] lastNames = { "Smith", "Jones", "Taylor", "Brown", "White" };
    private static final int totalUserAccountsPerRequest = 2; // Number of User accounts that will get added per request
    private static final int totalStudentsPerRequest = 3; // Number of Students that will get added per request

    private static String randomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private static String randomPhoneNumber() {
        StringBuilder sb = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10)); // 0-9
        }
        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");

            conn = ds.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // --- Random Data Preparation ---

            // random departments
            List<Department> departments = Arrays.asList(
                    new Department("Computer Science"),
                    new Department("Information Technology"),
                    new Department("Electrical Engineering"),
                    new Department("Mechanical Engineering"));

            int deptCount = departments.size();

            // random users
            List<User> users = new ArrayList<>();
            for (int i = 0; i < totalUserAccountsPerRequest; i++) {
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                String loginName = (firstName + "_" + lastName).toLowerCase();
                String password = "test";
                String email = firstName + lastName + "@ju.in";
                String phone = randomPhoneNumber();
                users.add(new User(loginName, password, email, firstName, lastName, phone));
            }

            // random students
            List<Student> students = new ArrayList<>();
            for (int i = 1; i <= totalStudentsPerRequest; i++) {
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                String name = firstName + " " + lastName;
                int deptID = random.nextInt(deptCount) + 1;
                students.add(new Student(name, deptID));
            }

            // Sample states
            List<State> states = Arrays.asList(
                    new State("Andhra Pradesh"),
                    new State("Maharashtra"),
                    new State("Tamil Nadu"));

            // Corresponding districts (2 per state)
            List<District> districts = Arrays.asList(
                    new District("Visakhapatnam", "A major port and industrial hub of Andhra Pradesh.",
                            "Andhra Pradesh"),
                    new District("Vijayawada", "Known for the Prakasam Barrage and Kanaka Durga Temple.",
                            "Andhra Pradesh"),

                    new District("Mumbai", "Financial capital of India and home to Bollywood.", "Maharashtra"),
                    new District("Pune", "Known for educational institutions and IT industry.", "Maharashtra"),

                    new District("Chennai", "Capital of Tamil Nadu with rich cultural heritage.", "Tamil Nadu"),
                    new District("Coimbatore", "An industrial city known for textiles and engineering.", "Tamil Nadu"));

            // PC components list
            List<Component> components = Arrays.asList(
                    new Component("Processor", "Intel", "i7-12700K", 320.00),
                    new Component("Processor", "AMD", "Ryzen 7 5800X", 290.00),
                    new Component("RAM", "Corsair", "Vengeance 16GB", 85.00),
                    new Component("RAM", "Kingston", "HyperX Fury 16GB", 75.00),
                    new Component("HDD", "Seagate", "Barracuda 2TB", 55.00),
                    new Component("HDD", "Western Digital", "Blue 2TB", 60.00),
                    new Component("Motherboard", "ASUS", "ROG Strix B550-F", 180.00),
                    new Component("Motherboard", "Gigabyte", "A520M S2H", 95.00),
                    new Component("Monitor", "Dell", "U2419H", 210.00),
                    new Component("Monitor", "LG", "24MK600M", 160.00),
                    new Component("CD/DVD R/W", "LG", "GH24NSD1", 25.00),
                    new Component("CD/DVD R/W", "ASUS", "DRW-24D5MT", 24.00));

            // Random Subjects
            List<Subject> subjects = Arrays.asList(
                    new Subject("CS101", "Computer Science Fundamentals"),
                    new Subject("IT102", "Information Systems"),
                    new Subject("EE103", "Electrical Circuits"),
                    new Subject("ME104", "Mechanics of Materials"),
                    new Subject("CS201", "Data Structures"),
                    new Subject("IT202", "Database Management Systems"));

            // Random Semesters
            List<Semester> semesters = Arrays.asList(
                    new Semester(1, "Semester 1"),
                    new Semester(2, "Semester 2"),
                    new Semester(3, "Semester 3"),
                    new Semester(4, "Semester 4"));
            // --- Insertion Starts ---

            // Insert into departments
            String deptSQL = "INSERT INTO departments (name) VALUES (?) ON DUPLICATE KEY UPDATE name = name;";
            ps = conn.prepareStatement(deptSQL);
            for (Department d : departments) {
                ps.setString(1, d.name);
                ps.executeUpdate();
            }
            ps.close();

            // Insert into users
            String userSQL = "INSERT INTO users (login_name, password, email, first_name, last_name, phone) VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE login_name = login_name;";
            ps = conn.prepareStatement(userSQL);
            for (User u : users) {
                ps.setString(1, u.loginName);
                ps.setString(2, u.password);
                ps.setString(3, u.email);
                ps.setString(4, u.firstName);
                ps.setString(5, u.lastName);
                ps.setString(6, u.phone);
                ps.executeUpdate();
            }
            ps.close();

            // Insert into students
            String studentSQL = "INSERT INTO students (name, deptID) VALUES (?, ?) ON DUPLICATE KEY UPDATE name = name;";
            ps = conn.prepareStatement(studentSQL);
            for (Student s : students) {
                ps.setString(1, s.name);
                ps.setInt(2, s.deptID);
                ps.executeUpdate();
            }
            ps.close();

            // Insert into states table
            String stateSQL = "INSERT INTO states (name) VALUES (?) " +
                    "ON DUPLICATE KEY UPDATE name = name;";
            ps = conn.prepareStatement(stateSQL);
            for (State s : states) {
                ps.setString(1, s.name);
                ps.executeUpdate();
            }
            ps.close();

            // First, build a map from state name to state ID
            Map<String, Integer> stateNameToId = new HashMap<>();
            String stateIdQuery = "SELECT id, name FROM states;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(stateIdQuery);
            while (rs.next()) {
                stateNameToId.put(rs.getString("name"), rs.getInt("id"));
            }
            rs.close();
            stmt.close();

            // Insert into districts table
            String distSQL = "INSERT INTO districts (name, description, state_id) VALUES (?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE name = name;";
            ps = conn.prepareStatement(distSQL);
            for (District d : districts) {
                Integer stateId = stateNameToId.get(d.stateName);
                if (stateId != null) {
                    ps.setString(1, d.name);
                    ps.setString(2, d.description);
                    ps.setInt(3, stateId);
                    ps.executeUpdate();
                } else {
                    System.err.println("State not found for district: " + d.name);
                }
            }
            ps.close();

            // INsert into components
            String sql = "INSERT INTO components (component_type, manufacturer, model, price) " +
                    "VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE price = VALUES(price)";

            ps = conn.prepareStatement(sql);

            for (Component c : components) {
                ps.setString(1, c.type);
                ps.setString(2, c.manufacturer);
                ps.setString(3, c.model);
                ps.setDouble(4, c.price);
                ps.executeUpdate();
            }

            ps.close();

            // Insert into semesters table
            String semesterSQL = "INSERT INTO semesters (semester_id, semester_name) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE semester_name = ?";
            ps = conn.prepareStatement(semesterSQL);

            for (Semester semester : semesters) {
                ps.setInt(1, semester.semesterId);
                ps.setString(2, semester.semesterName);
                ps.setString(3, semester.semesterName); // For the update clause
                ps.executeUpdate();
            }
            ps.close();

            // Insert into subjects table
            String subjectSQL = "INSERT INTO subjects (subject_code, subject_name) VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE subject_name = ?";
            ps = conn.prepareStatement(subjectSQL);

            for (Subject subject : subjects) {
                ps.setString(1, subject.subjectCode);
                ps.setString(2, subject.subjectName);
                ps.setString(3, subject.subjectName); // For the update clause
                ps.executeUpdate();
            }

            ps.close();

            // Insert Marks
            // Get all student roll numbers
            String studentsQuery = "SELECT roll FROM students";
            ps = conn.prepareStatement(studentsQuery);
            rs = ps.executeQuery();
            List<String> studentRolls = new ArrayList<>();
            while (rs.next()) {
                studentRolls.add(rs.getString("roll"));
            }
            ps.close();
            rs.close();

            // Get all semester ids
            String semestersQuery = "SELECT semester_id FROM semesters";
            ps = conn.prepareStatement(semestersQuery);
            rs = ps.executeQuery();
            List<Integer> semesterIDs = new ArrayList<>();
            while (rs.next()) {
                semesterIDs.add(rs.getInt("semester_id"));
            }
            ps.close();
            rs.close();

            // Get all subject codes
            String subjectsQuery = "SELECT subject_code FROM subjects";
            ps = conn.prepareStatement(subjectsQuery);
            rs = ps.executeQuery();
            List<String> subjectCodes = new ArrayList<>();
            while (rs.next()) {
                subjectCodes.add(rs.getString("subject_code"));
            }
            ps.close();
            rs.close();

            // Prepare the SQL for inserting marks
            String insertMarksSQL = "INSERT INTO marks (roll, subject_code, semester_id, marks) VALUES (?, ?, ?, ?)" +
            "ON DUPLICATE KEY UPDATE marks = ?";
            ps = conn.prepareStatement(insertMarksSQL);

            // Insert random marks for each student, subject, and semester
            // int studentSemester = 4;
            // int semesterSubject = 6;

            // Collections.shuffle(semesterIDs); // Shuffle to pick random semesters
            // Collections.shuffle(subjectCodes); // Shuffle to pick random subjects

            for (String roll : studentRolls) {
                // List<Integer> selectedSemesters = semesterIDs.subList(0, Math.min(studentSemester, semesterIDs.size()));

                for (Integer semesterId : semesterIDs) {
                    // Collections.shuffle(subjectCodes); // Shuffle again for each semester
                    // List<String> selectedSubjects = subjectCodes.subList(0,
                            // Math.min(semesterSubject, subjectCodes.size()));

                    for (String subjectCode : subjectCodes) {
                        int marks = random.nextInt(101); // 0 to 100
                        ps.setString(1, roll);
                        ps.setString(2, subjectCode);
                        ps.setInt(3, semesterId);
                        ps.setInt(4, marks);
                        ps.setInt(5, marks); // For update clause
                        ps.executeUpdate();
                    }
                }
            }
            ps.close();

            conn.commit(); // Commit transaction

            response.sendRedirect("/AayaanKaji/Database/ShowTablesServlet");
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
