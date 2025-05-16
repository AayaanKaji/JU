### JSP Tags and Their Purpose

| JSP Tag Syntax | Purpose                                                                  |
| -------------- | ------------------------------------------------------------------------ |
| `<%@ ... %>`   | **Directive**: Defines global settings (e.g., imports, content type).    |
| `<%! ... %>`   | **Declaration**: Declares fields, methods, or inner classes.             |
| `<%= ... %>`   | **Expression**: Outputs the result of a Java expression to the response. |
| `<% ... %>`    | **Scriptlet**: Contains Java code to be executed within `_jspService()`. |

---

### Examples

* **Directive**

  ```jsp
  <%@ page import="java.util.Date" %>
  ```

* **Declaration**

  ```jsp
  <%! int counter = 0; %>
  ```

* **Expression**

  ```jsp
  Current date: <%= new Date() %>
  ```

* **Scriptlet**

  ```jsp
  <% counter++; %>
  ```

---