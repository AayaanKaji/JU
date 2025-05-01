<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
    <%
        String message = "This is a JSP scriptlet!";
        out.println("<h1>" + message + "</h1>");
    %>
</body>
</html>
