<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*" %>
<%@ page import="javax.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MCQ Quiz</title>
</head>
<body>
    <h2>Answer the following questions:</h2>
    <form action="/Q26_Question_Exporter/evaluate.jsp" method="post">
        <%
            try {
                InitialContext ctx = new InitialContext();
                DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");

                Connection conn = ds.getConnection();

                String sql = "SELECT id, question_text, optionA, optionB, optionC, optionD FROM 1100_questions";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String question = rs.getString("question_text");
                    String a = rs.getString("optionA");
                    String b = rs.getString("optionB");
                    String c = rs.getString("optionC");
                    String d = rs.getString("optionD");
        %>
                    <div>
                        <p><strong><%= question %></strong></p>
                        <input type="radio" name="q<%= id %>" value="A"> <%= a %><br>
                        <input type="radio" name="q<%= id %>" value="B"> <%= b %><br>
                        <input type="radio" name="q<%= id %>" value="C"> <%= c %><br>
                        <input type="radio" name="q<%= id %>" value="D"> <%= d %><br>
                    </div>
                    <br>
        <%
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                out.println("Error loading questions: " + e.getMessage());
            }
        %>
        <input type="submit" value="Submit Answers">
    </form>
</body>
</html>
