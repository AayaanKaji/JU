### JSP Expressions

**Purpose:**
To insert the value of a Java expression directly into the JSP output without explicitly using `out.print()` or `out.write()`.

**Syntax:**

```jsp
<%= expression %>
```

* The expression inside `<%= ... %>` is evaluated, converted to a `String`, and inserted into the response output.
* It simplifies displaying dynamic data on the page.

---

### Behavior

* Equivalent to:

  ```java
  out.print(expression);
  ```
* Can be used anywhere in the JSP page, including inside HTML content or attribute values.

---

### Examples

* Simple arithmetic:

  ```jsp
  3 + 4 = <%= 3 + 4 %>
  ```

  Translates to:

  ```java
  out.write("3 + 4 = ");
  out.print(3 + 4);
  ```

* Display current date/time:

  ```jsp
  Date and time is: <%= new java.util.Date() %>
  ```

* Used in tag names and attribute values:

  ```jsp
  <%
    java.util.Date dt = new java.util.Date();
    String dateStr = dt.toString();
    String time = "currentTime";
  %>

  <<%= time %> value="<%= dateStr %>" />
  ```

  Generates:

  ```html
  <currentTime value="Sun Sep 22 04:12:50 PDT 2013" />
  ```

---

### Alternative Syntax

Equivalent JSP expression tag:

```jsp
<jsp:expression> expression </jsp:expression>
```

---

### Summary

| Feature     | Description                                       |
| ----------- | ------------------------------------------------- |
| Use         | Embed Java expression result directly             |
| Output      | Expression result converted to String and printed |
| Syntax      | `<%= expression %>`                               |
| Alternative | `<jsp:expression>expression</jsp:expression>`     |

---