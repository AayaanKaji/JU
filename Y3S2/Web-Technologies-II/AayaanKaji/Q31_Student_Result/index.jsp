<%@ page import="java.sql.*, javax.naming.*, javax.sql.*" %>
<!DOCTYPE html>
<html>
<head><title>Select Marks</title></head>
<body>
<h2>Check Student Marks</h2>
<form action="/AayaanKaji/Q31_Student_Result/displayMarks.jsp" method="post" accept-charset="UTF-8">
    <%
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
        Connection conn = ds.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
    %>

    Roll Number:
    <select name="roll">
        <%
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
        %>
        <option value="<%= rs.getString("roll") %>"><%= rs.getString("roll") %> - <%= rs.getString("name") %></option>
        <%
            }
            rs.close(); stmt.close();
        %>
    </select><br/><br/>

    Semester:
    <select name="semester">
        <%
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM semesters");
            while (rs.next()) {
        %>
        <option value="<%= rs.getInt("semester_id") %>"><%= rs.getString("semester_name") %></option>
        <%
            }
            rs.close(); stmt.close();
        %>
    </select><br/><br/>

    Subject:
    <select name="subject_code">
        <%
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM subjects");
            while (rs.next()) {
        %>
        <option value="<%= rs.getString("subject_code") %>"><%= rs.getString("subject_name") %></option>
        <%
            }
            rs.close(); stmt.close(); conn.close();
        %>
    </select><br/><br/>

    <input type="submit" value="Check Marks">
</form>
</body>
</html>
