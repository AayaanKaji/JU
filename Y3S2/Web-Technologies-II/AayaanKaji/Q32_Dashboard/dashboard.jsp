<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %> <%@ page
import="Database.User" %>
<!DOCTYPE html>
<html>
  <head>
    <title>User Dashboard</title>
  </head>
  <body>
    <h2>User Login Details</h2>
    <% User user = (User) request.getAttribute("user"); if (user != null) { %>
    <p><strong>Login Name:</strong> <%= user.getLoginName() %></p>
    <p><strong>Password:</strong> <%= user.getPassword() %></p>
    <p><strong>Email:</strong> <%= user.getEmail() %></p>
    <p><strong>First Name:</strong> <%= user.getFirstName() %></p>
    <p><strong>Last Name:</strong> <%= user.getLastName() %></p>
    <p><strong>Phone:</strong> <%= user.getPhone() %></p>
    <% } else { %> <% response.sendRedirect("/Q32_Dashboard/"); %> <% } %>
  </body>

  <form method="post" action="/AayaanKaji/Q27_Change_Password/" accept-charset="utf-8">
    <button type="submit">Change Password</button>
  </form>

  <form method="post" action="signout.jsp" accept-charset="utf-8">
    <button type="submit">Sign Out</button>
  </form>
</html>
