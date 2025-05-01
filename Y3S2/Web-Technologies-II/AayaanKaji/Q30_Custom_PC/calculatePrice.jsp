<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Selected Components Summary</title>
</head>
<body>
<h2>Selected Components and Total Price</h2>

<%
    double totalPrice = 0.0;

    try {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
        Connection conn = ds.getConnection();

        // Retrieve all parameters from the form
        Map<String, String[]> paramMap = request.getParameterMap();

        PreparedStatement ps = conn.prepareStatement(
            "SELECT price FROM components WHERE manufacturer = ? AND model = ?"
        );

        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String componentType = entry.getKey();
            String value = entry.getValue()[0];

            if (value == null || !value.contains("|")) continue;

            String[] parts = value.split("\\|");
            String manufacturer = parts[0];
            String model = parts[1];

            ps.setString(1, manufacturer);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double price = rs.getDouble("price");
                totalPrice += price;

                out.println("<p><strong>" + componentType + "</strong>: " + manufacturer + " - " + model +
                        " | Price: ₹" + String.format("%.2f", price) + "</p>");
            } else {
                out.println("<p><strong>" + componentType + "</strong>: Not found in DB.</p>");
            }
            rs.close();
        }

        ps.close();
        conn.close();

        out.println("<h3>Total Price: ₹" + String.format("%.2f", totalPrice) + "</h3>");

    } catch (Exception e) {
        out.println("<p>Error: " + e.getMessage() + "</p>");
        e.printStackTrace();
    }
%>

</body>
</html>
