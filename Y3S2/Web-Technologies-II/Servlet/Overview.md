## 1. **Servlet Definition and Execution Model**

* A **Servlet** is a Java class that operates on the **server-side**, extending the capabilities of a web server.
* It runs inside a **Java-enabled web server (Servlet container)** such as Apache Tomcat.
* Most often, the term *Servlet* refers specifically to **HTTP Servlets**, which handle HTTP requests.

### Servlet Request-Response Cycle:

1. **Client** sends a request.
2. **Web Server** receives it and forwards it to the **servlet**.
3. **Servlet** processes the request, generates a response.
4. **Web Server** sends the response back to the **client browser**.

---

## 2. **Servlet Life Cycle**

Defined by three main methods:

* `init()` → Initialization logic
* `service()` → Handles request processing (dispatches to methods like `doGet()`, `doPost()` for `HttpServlet`)
* `destroy()` → Cleanup before servlet removal

### Life Cycle States:

* Instantiated
* Initialized
* Ready to service
* Servicing
* Not ready to service
* Destroyed

---

## 3. **Servlet API Packages**

* **`javax.servlet`**: Base interfaces and classes (protocol-independent)
* **`javax.servlet.http`**: HTTP-specific subclasses (e.g., `HttpServlet`)

### Important Classes:

* `GenericServlet`: Abstract class for protocol-independent servlets.
* `HttpServlet`: Extends `GenericServlet`, handles HTTP-specific methods.

---

## 4. **Example: HelloWorld Servlet**

```java
public class HelloWorldServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body><h1>Hello World!</h1></body></html>");
        out.close();
    }
}
```

* Compiled with `servlet-api.jar`
* Deployed in `$TOMCAT_HOME/webapps/<app>/WEB-INF/classes`
* Mapped via `web.xml`

---

## 5. **Servlet Deployment (`web.xml` configuration)**

```xml
<servlet>
    <servlet-name>HelloWorld</servlet-name>
    <servlet-class>HelloWorldServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>HelloWorld</servlet-name>
    <url-pattern>/servlet/HelloWorld</url-pattern>
</servlet-mapping>
```

---

## 6. **Parameter Handling**

### Via URL:

```
http://<host>:8080/app/servlet/check?login=abc&password=xyz
```

### Via HTML Form:

```html
<form method='post' action='servlet/check'>
    Login: <input type='text' name='login'><br />
    Password: <input type='password' name='password'><br/>
    <input type='submit' value='Login'>
</form>
```

### Servlet Parameter Retrieval:

```java
String login = request.getParameter("login");
String password = request.getParameter("password");
```

---

## 7. **Server-Side Includes (SSI)**

* SSI enables dynamic content insertion in `.shtml` files.
* Tomcat uses `org.apache.catalina.ssi.SSIServlet`.

### SSI Directives:

* `<!--#include virtual="servlet/HelloWorld" -->`
* `<!--#echo var="DATE_LOCAL" -->`
* `<!--#printenv -->`
* `<!--#set var="x" value="y" -->`
* Conditional directives: `if`, `elif`, `else`, `endif`

---

## 8. **Cookies**

* Used for **session tracking**.
* Represented by `javax.servlet.http.Cookie`.

```java
Cookie c = new Cookie("session_started", timestamp);
response.addCookie(c);
```

* On future requests, cookies are retrieved via `request.getCookies()`.

---

## 9. **Filters**

* Filters implement `javax.servlet.Filter` and act on the request/response before reaching the servlet or after it.

### Filter Lifecycle Methods:

* `init(FilterConfig config)`
* `doFilter(ServletRequest request, ServletResponse response, FilterChain chain)`
* `destroy()`

### Common Uses:

* Authentication
* Logging/Auditing
* Data compression/encryption
* Input sanitization

---

## 10. **Advantages of Servlets**

* Efficient (single instance handles multiple requests via multithreading)
* Persistent (doesn’t start a new process for each request like CGI)
* Portable (Java-based)
* Secure, Robust, Extensible
* Supported by many application servers

### Alternatives:

* CGI (less efficient)
* ASP
* Server-side JavaScript
* Proprietary APIs

---

## 11. **Popular Servlet Containers**

| Product                   | Source    |
| ------------------------- | --------- |
| Apache Tomcat             | Apache    |
| Jetty                     | Eclipse   |
| WebLogic                  | BEA       |
| WebSphere                 | IBM       |
| Resin                     | Caucho    |
| JRun                      | Adobe     |
| Oracle Application Server | Oracle    |
| Enhydra                   | ObjectWeb |

---