<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Hidden Feilds</title>
</head>
<body>
    <%
        Integer curr = 0;
        Integer prev = (request.getParameter("currentInteger") != null) ? Integer.parseInt(request.getParameter("currentInteger")) : 0;
        String action = request.getParameter("action");
        
        if ("Next".equals(action)) {
            curr = prev + 1;
        } else if ("Prev".equals(action)) {
            curr = prev - 1;
        }
    %>

    <h2>Current Integer: <%= curr %></h2>

    <form name="hiddenFieldForm" method="post">
        <!-- Hidden field to track the current number -->
        <input type="hidden" name="currentInteger" value="<%= curr %>">
        <input type="submit" name="action" value="Prev">
        <input type="submit" name="action" value="Next">
    </form>
</body>
</html>
