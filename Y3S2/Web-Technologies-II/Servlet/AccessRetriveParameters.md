## **Passing Parameters to Servlets**

Servlets can accept input parameters from clients, enabling **customized request handling**.

There are **two main methods** for passing parameters:

---

### 1. **Using URL (Query Parameters)**

* Sent via **HTTP GET** method.
* Parameters are appended to the URL using the format:

```
http://host:port/path?param1=value1&param2=value2
```

#### Example:

```url
http://172.16.5.81:8080/net/servlet/check?login=abc&password=xyz
```

**Disadvantages**:

* Visible in browser address bar — **not secure** for sensitive data.
* URL length limits may apply.

---

### 2. **Using HTML Forms**

* Typically sent via **HTTP POST**.
* Parameters are sent in the **body** of the request — **not visible** in URL.

#### HTML Example:

```html
<form method="post" action="http://172.16.5.81:8080/net/servlet/check">
  Login: <input type="text" name="login"><br />
  Password: <input type="password" name="password"><br />
  <input type="submit" value="Login">
</form>
```

---

## **Retrieving Parameters in Servlets**

The `ServletRequest` (or `HttpServletRequest`) interface provides methods to access parameter data.

| Method                                     | Description                                                            |
| ------------------------------------------ | ---------------------------------------------------------------------- |
| `String getParameter(String name)`         | Returns the **value of a single** parameter (first if multiple).       |
| `String[] getParameterValues(String name)` | Returns **all values** of a multi-valued parameter (e.g., checkboxes). |
| `Enumeration<String> getParameterNames()`  | Returns all parameter names sent with the request.                     |

---

## Example Servlet: Retrieving Parameters

```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GetParameterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        out.println("<html><head><title>Login Info</title></head><body>");
        out.println("<h2>Login: " + login + "<br>Password: " + password + "</h2>");
        out.println("</body></html>");
    }
}
```

---

## **Key Points**

* **GET** passes parameters via the URL; **POST** via the request body.
* Use `getParameter()` for form fields.
* Never use GET to send **sensitive data** (e.g., passwords).
* `getParameterValues()` is useful for inputs like checkboxes with the same `name`.
* `getParameterNames()` allows **dynamic** processing of unknown or optional parameters.

---