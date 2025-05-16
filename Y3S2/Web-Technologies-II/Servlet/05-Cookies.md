## **Cookies in Java Servlets: Overview**

A **cookie** is a small piece of data in the form of a **(key, value)** pair, stored by the **client's browser** and sent back to the server with each HTTP request. Cookies are one mechanism for **session tracking**.

### Cookie Class

Java provides the class:

```java
javax.servlet.http.Cookie
```

### Constructor

```java
Cookie(String name, String value)
```

---

## Lifecycle of a Cookie (Example Explanation)

```java
public class CookieDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies(); // 1. Read existing cookies
        boolean found = false;

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("session_started")) {
                    found = true;
                    out.println("You started this session on:");
                    out.println(cookies[i].getValue()); // 2. Display stored value
                }
            }
        }

        if (!found) {
            // 3. No cookie found → create one and send it to browser
            String dt = (new java.util.Date()).toString();
            response.addCookie(new Cookie("session_started", dt));
            out.println("Welcome to our site...");
        }
    }
}
```

---

## **Step-by-Step Breakdown**

| Step                      | Description                                         |
| ------------------------- | --------------------------------------------------- |
| 1. `request.getCookies()` | Reads all cookies sent by the client.               |
| 2. `cookies[i].getName()` | Searches for cookie named `"session_started"`.      |
| 3. If found               | Prints its value (timestamp of first visit).        |
| 4. If not found           | Creates a new cookie with current date/time, using: `new Cookie("session_started", dt)`                 |
| 5. Cookie sent to client  | `response.addCookie(...)` installs it in browser.   |

---

## Notes

* Cookies are stored on **client-side**.
* Stateless HTTP becomes "stateful" using mechanisms like cookies.
* Cookies are automatically included by the browser in subsequent requests.
* Use `Cookie.setMaxAge(seconds)` to control lifetime (default: session cookie).
* Use `getCookies()` to retrieve all cookies from the request.
* Use `response.addCookie(cookie)` to send a new cookie.

---