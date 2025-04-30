<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>URL Rewriting</title>
</head>
<body>
    <%
        Integer prev = (request.getParameter("currentInteger") != null) ? Integer.parseInt(request.getParameter("currentInteger")) : 0;
    %>

    <h2>Current Integer: <%= prev %></h2>

    <form name="urlRewriteForm" action="/Q21_Session_Tracking/urlRewrite.jsp?currentInteger=<%=prev-1%>" method="post">
        <input type="submit" name="action" value="Prev">
    </form>
    <form name="urlRewriteForm" action="/Q21_Session_Tracking/urlRewrite.jsp?currentInteger=<%=prev+1%>" method="post">
        <input type="submit" name="action" value="Next">
    </form>
</body>
</html>
