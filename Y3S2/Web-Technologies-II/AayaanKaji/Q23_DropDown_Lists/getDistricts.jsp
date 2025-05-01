<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%
    String state = request.getParameter("state");
    if (state != null && !state.trim().isEmpty()) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");

            conn = ds.getConnection();

            // Corrected SQL query
            String sql = "SELECT d.name AS district_name FROM districts d " +
                         "JOIN states s ON d.state_id = s.id " +
                         "WHERE LOWER(s.name) = LOWER(?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, state);
            rs = ps.executeQuery();

            // Output district options
            out.print("<option value='' disabled selected>--Select District--</option>");
            while (rs.next()) {
                String dist = rs.getString("district_name");
                out.print("<option value='" + dist + "'>" + dist + "</option>");
            }

        } catch (Exception e) {
            // Handle exceptions and provide a message
            out.print("<option>Error loading districts</option>");
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();  // Log any exception during closure
            }
        }
    }
%>
