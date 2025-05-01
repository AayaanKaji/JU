<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Results</title>
</head>
<body>
    <h2>Your Results:</h2>
    <%
        int total = 0;
        int correct = 0;

        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");

            Connection conn = ds.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, answer FROM questions");

            while (rs.next()) {
                int qid = rs.getInt("id");
                String correctAnswer = rs.getString("answer");

                String userAnswer = request.getParameter("q" + qid);
                total++;
                if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
                    correct++;
                }
            }

            rs.close();
            stmt.close();
            conn.close();

            out.println("<p>You answered " + correct + " out of " + total + " questions correctly.</p>");

        } catch (Exception e) {
            out.println("Error evaluating quiz: " + e.getMessage());
        }
    %>
</body>
</html>
