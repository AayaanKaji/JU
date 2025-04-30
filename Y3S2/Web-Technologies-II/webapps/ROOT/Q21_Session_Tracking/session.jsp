<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Session API</title>
</head>
<body>
    <%
        Integer currentInteger = (Integer) session.getAttribute("currentInteger");
        
        currentInteger = (currentInteger != null) ? currentInteger : 0;
        
        String action = request.getParameter("action");
        if ("Next".equals(action)) {
            currentInteger++;
        } else if ("Prev".equals(action)) {
            currentInteger--;
        }

        session.setAttribute("currentInteger", currentInteger);
    %>

    <h2>Current Integer: <%= (session.getAttribute("currentInteger") != null) ? session.getAttribute("currentInteger") : 0 %></h2>

    <form name="sessionForm" action="/Q21_Session_Tracking/session.jsp" method="post">
        <input type="submit" name="action" value="Prev">
        <input type="submit" name="action" value="Next">
    </form>
</body>
</html>
