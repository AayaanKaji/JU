<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Search Students</title>
  </head>
  <body>
    <h1>Search Students by Name & Department</h1>
    <form action="/Q19_Form_Servlet/SearchStudentServlet" method="get">
      <label for="name">Enter Name (or part of name):</label>
      <input type="text" id="name" name="name" />
      <input type="submit" value="Search" />
    </form>

    <form action="/Q19_Form_Servlet/SearchStudentServlet" method="get">
        <label for="department">Choose Department:</label>
        <select id="department" name="department">
            <c:forEach items="${departments}" var="dept">
                <option value="${dept}">${dept}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Show Students">
    </form>

    <h2>Search Results</h2>
    <table border="1">
      <tr>
        <th>Roll Number</th>
        <th>Name</th>
        <th>Department</th>
      </tr>
      <c:forEach items="${students}" var="student">
        <tr>
          <td>${student.getRollNumber()}</td>
          <td>${student.getStudentName()}</td>
          <td>${student.getDeptName()}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
