<%@ page import="java.sql.*, javax.naming.*, javax.sql.*" %>
<!DOCTYPE html>
<%
    String rollNo = request.getParameter("roll");
    String subjectCode = request.getParameter("subject_code");
    int semesterId = Integer.parseInt(request.getParameter("semester"));

    InitialContext ctx = new InitialContext();
    DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
    Connection conn = ds.getConnection();

    PreparedStatement ps = conn.prepareStatement(
        "SELECT m.marks, s.name AS student_name, sb.subject_name, sem.semester_name " +
        "FROM marks m " +
        "JOIN students s ON m.roll = s.roll " +
        "JOIN subjects sb ON m.subject_code = sb.subject_code " +
        "JOIN semesters sem ON m.semester_id = sem.semester_id " +
        "WHERE m.roll = ? AND m.subject_code = ? AND m.semester_id = ?"
    );
    ps.setString(1, rollNo);
    ps.setString(2, subjectCode);
    ps.setInt(3, semesterId);
    ResultSet rs = ps.executeQuery();

    if (rs.next()) {
%>
        <h3>Mark Sheet</h3>
        <p><strong>Student:</strong> <%= rs.getString("student_name") %></p>
        <p><strong>Subject:</strong> <%= rs.getString("subject_name") %></p>
        <p><strong>Semester:</strong> <%= rs.getString("semester_name") %></p>
        <p><strong>Marks:</strong> <%= rs.getInt("marks") %></p>
<%
    } else {
%>
        <p><strong>No marks found for the selected combination.</strong></p>
<%
    }
    rs.close(); ps.close(); conn.close();
%>
