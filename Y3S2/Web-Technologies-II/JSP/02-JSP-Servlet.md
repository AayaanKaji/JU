### JSP vs Servlet

* **Servlets** mix Java logic with HTML using `out.print()`, making large pages hard to maintain.
* **JSP** separates presentation from business logic.

  * HTML is written normally.
  * Java logic is embedded or externalized via JavaBeans.

---

### JSP Update Workflow

* JSPs are monitored by the **JSP engine** (part of the web container).
* When a JSP file is modified:

  * It is automatically **translated** into a servlet.
  * Then **compiled**, **loaded**, and **instantiated**.
* No manual recompilation or server restart is needed.

---

### Servlet Update Workflow

* On servlet modification:

  * Manual **recompilation** is required.
  * Often requires **server restart** to pick up changes.

---

### Translation and Compilation

* Every `.jsp` file is converted to a `.java` servlet source file.
* This servlet is then compiled into a `.class` file.

**Example:**

* `date.jsp` → translated → `date_jsp.java` → compiled → `date_jsp.class`

**JSP Snippet:**

```jsp
<html>
<head><title></title></head>
<body>
<%= new java.util.Date() %>
</body>
</html>
```

**Equivalent Java Snippet:**

```java
public final class date_jsp extends HttpJspBase {
    public void _jspService(HttpServletRequest request, HttpServletResponse response) {
        out.write("<html><head><title></title></head><body>");
        out.write(new java.util.Date().toString());
        out.write("</body></html>");
    }
}
```

---

### Example: Dynamic Table (Temperature Conversion)

```jsp
<table border="1">
<caption>Temperature Conversion chart</caption>
<tr><th>Celsius</th><th>Fahrenheit</th></tr>
<%
for (int c = 0; c <= 100; c += 20) {
    double f = (c * 9) / 5.0 + 32;
    out.println("<tr><td>" + c + "</td><td>" + f + "</td></tr>");
}
%>
</table>
```

* Generates a temperature chart from Celsius to Fahrenheit.
* Shows how Java code inside JSP can dynamically generate HTML content.

---