<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ page
import="javax.naming.*" %> <%@ page import="java.sql.*" %> <%@ page
import="javax.sql.*" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Indian States</title>
    <style>
      .container {
        max-width: 400px;
        margin: 50px auto;
      }
      select,
      p {
        width: 100%;
        padding: 8px;
        margin: 10px 0;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <label for="states">Select State:</label>
      <select id="states" onchange="populateDistricts()">
        <option value="" selected disabled>--Select State--</option>
        <% try { InitialContext ctx = new InitialContext(); DataSource ds =
        (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB"); Connection conn =
        ds.getConnection(); PreparedStatement stmt =
        conn.prepareStatement("SELECT name FROM 1100_states ORDER BY name");
        ResultSet rs = stmt.executeQuery(); while (rs.next()) { String state =
        rs.getString("name"); %>
        <option value="<%= state %>"><%= state %></option>
        <% } rs.close(); stmt.close(); conn.close(); } catch (Exception e) {
        out.print("<option>Error loading states</option>"); } %>
      </select>

      <label for="districts">Select District:</label>
      <select id="districts" onchange="showDistrictInfo()">
        <option value="" selected disabled>--Select District--</option>
      </select>

      <p id="district-info"></p>
    </div>

    <script>
      function populateDistricts() {
        const state = document.getElementById("states").value; // Get the selected state value

        const postData = new URLSearchParams();
        postData.append("state", state);

        fetch("/Q23_DropDown_Lists/getDistricts.jsp", {
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded",
          },
          body: postData.toString(),
        })
          .then((response) => response.text())
          .then((data) => {
            document.getElementById("districts").innerHTML = data;
            document.getElementById("district-info").textContent = "";
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      }

      function showDistrictInfo() {
          const district = document.getElementById("districts").value;

          // Use POST method for AJAX
          const postData = new URLSearchParams();
          postData.append("district", district);

          fetch("/Q23_DropDown_Lists/getDistrictInfo.jsp", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
          },
            body: postData.toString(),
          })
          .then(response => response.text())
          .then(data => {
              document.getElementById("district-info").textContent = data;
          })
          .catch((error) => {
            console.error("Error:", error);
          });
      }
    </script>
  </body>
</html>
