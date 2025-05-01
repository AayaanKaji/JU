<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Select Computer Components</title>
</head>
<body>
<h2>Select Computer Components</h2>
<form action="/AayaanKaji/Q30_Custom_PC/calculatePrice.jsp" method="POST">
<%
    try {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
        Connection conn = ds.getConnection();

        Statement stmt = conn.createStatement();
        ResultSet typesRs = stmt.executeQuery("SELECT DISTINCT component_type FROM components");

        while (typesRs.next()) {
            String type = typesRs.getString("component_type");

            out.println("<label for='" + type + "'>" + type + ":</label>");
            out.println("<select name='" + type + "'>");
            out.println("<option value='' disabled selected>--Select--</option>");

            PreparedStatement ps = conn.prepareStatement(
                "SELECT manufacturer, model FROM components WHERE component_type = ?");
            ps.setString(1, type);
            ResultSet compRs = ps.executeQuery();

            while (compRs.next()) {
                String manufacturer = compRs.getString("manufacturer");
                String model = compRs.getString("model");
                String optionValue = manufacturer + "|" + model; // Send both for later lookup
                out.println("<option value='" + optionValue + "'>" + manufacturer + " - " + model + "</option>");
            }

            out.println("</select><br/><br/>");
            compRs.close();
            ps.close();
        }

        typesRs.close();
        stmt.close();
        conn.close();

    } catch (Exception e) {
        out.println("<p>Error: " + e.getMessage() + "</p>");
        e.printStackTrace();
    }
%>
    <input type="submit" value="Calculate Total Price" />
</form>
</body>
</html>
