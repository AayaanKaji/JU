## JSP Implicit Objects

**Definition:**
Implicit objects are predefined variables automatically provided by the JSP container. They are created in the `_jspService()` method of the servlet generated from the JSP page. These objects provide access to important runtime information like request data, response handling, session state, etc.

---

### Summary Table

| **Object**    | **Class**                                | **Scope**      | **Description**                                                                     |
| ------------- | ---------------------------------------- | -------------- | ----------------------------------------------------------------------------------- |
| `out`         | `javax.servlet.jsp.JspWriter`            | `page`         | Used to write content to the client (e.g., HTML).                                   |
| `request`     | `javax.servlet.http.HttpServletRequest`  | `request`      | Provides access to HTTP request data (e.g., parameters, headers).                   |
| `response`    | `javax.servlet.http.HttpServletResponse` | `page`         | Used to control the response (e.g., set headers, redirect, etc.).                   |
| `config`      | `javax.servlet.ServletConfig`            | `page`         | Accesses servlet initialization parameters.                                         |
| `session`     | `javax.servlet.http.HttpSession`         | `session`      | Provides access to the HTTP session between client and server.                      |
| `application` | `javax.servlet.ServletContext`           | `application`  | Shares data across the entire web application.                                      |
| `pageContext` | `javax.servlet.jsp.PageContext`          | `page`         | Encapsulates all other implicit objects and provides utility methods.               |
| `page`        | `java.lang.Object`                       | `page`         | Refers to the servlet instance generated from the JSP page.                         |
| `exception`   | `java.lang.Throwable`                    | `page` (error) | Represents uncaught exceptions (only available in pages with `isErrorPage="true"`). |

---

### Key Usage Examples

#### `request`

Used to access parameters sent via query string or form submission:

```jsp
<%
String name = request.getParameter("name");
out.println("Hello " + name);
%>
```

URL: `http://localhost:8080/app/page.jsp?name=Monali`
Output: `Hello Monali`

**HTML Form Example:**

```html
<form method="post" action="add.jsp">
  <input type="text" name="a"> +
  <input type="text" name="b">
  <input type="submit" value="Add">
</form>
```

**JSP Handling:**

```jsp
<%
int a = Integer.parseInt(request.getParameter("a"));
int b = Integer.parseInt(request.getParameter("b"));
out.println(a + " + " + b + " = " + (a + b));
%>
```

---

### Notes on Scope

* **page** – accessible only within the current JSP page (default for most implicit objects).
* **request** – accessible throughout the lifecycle of a single HTTP request, even across forwarded JSP pages or servlets.
* **session** – persists across multiple requests from the same client session.
* **application** – shared across all JSP pages and servlets in the same web application context.

---