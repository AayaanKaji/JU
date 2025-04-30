<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%
    String district = request.getParameter("district");
    if (district != null && !district.trim().isEmpty()) {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");

            Connection conn = ds.getConnection();

            String sql = "SELECT description FROM 1100_districts WHERE LOWER(name) = LOWER(?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, district);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.print(rs.getString("description"));
            } else {
                out.print("No description found.");
            }

            rs.close(); ps.close(); conn.close();
        } catch (Exception e) {
            out.print("Error loading description.");
            e.printStackTrace();
        }
    }
%>
