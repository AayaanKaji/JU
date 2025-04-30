<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Have a cookie</title>
</head>
<body>
    <%
        Cookie[] cookies = request.getCookies();
        int currentInteger = 0;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currentInteger")) {
                    currentInteger = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }

        String action = request.getParameter("action");
        if ("Next".equals(action)) {
            currentInteger++;
        } else if ("Prev".equals(action)) {
            currentInteger--;
        }

        Cookie currentCookie = new Cookie("currentInteger", String.valueOf(currentInteger));
        currentCookie.setMaxAge(15); // seconds
        response.addCookie(currentCookie);
    %>

    <h2>Current Integer: <%= currentInteger %></h2>

    <form name="hiddenFieldForm" method="post">
        <input type="submit" name="action" value="Prev">
        <input type="submit" name="action" value="Next">
    </form>
</body>
</html>
