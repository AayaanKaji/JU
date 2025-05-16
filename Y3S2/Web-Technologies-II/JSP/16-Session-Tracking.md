### Why Session Tracking is Needed

* **HTTP is stateless** – server does not remember client across requests.
* Session tracking relates **current request to previous ones**.
* Goal: maintain state (e.g., counter) across multiple client requests.

---

## 1. Hidden Fields

**Mechanism**: Store data in a hidden form field and submit with every request.

### Code Snippet:

```jsp
<%
int current = 0;
String last = request.getParameter("int");
String button = request.getParameter("button");

if (button != null) {
    current = Integer.parseInt(last);
    current = button.equals("next") ? current + 1 : current - 1;
}
%>
<%= current %>
<form method="post">
  <input type="hidden" name="int" value="<%= current %>">
  <input type="submit" name="button" value="prev">
  <input type="submit" name="button" value="next">
</form>
```

---

## 2. URL Rewriting

**Mechanism**: Pass state through the query string.

### Code Snippet:

```jsp
<%
int current = 0;
String param = request.getParameter("int");
if (param != null) current = Integer.parseInt(param);
%>
<%= current %><br>
<a href="intUrl.jsp?int=<%= current - 1 %>">prev</a>
<a href="intUrl.jsp?int=<%= current + 1 %>">next</a>
```

---

## 3. Cookies

**Mechanism**: Store value in a cookie sent back to client and retrieved in subsequent requests.

### Code Snippet:

```jsp
<%
int current = 0;
Cookie[] cookies = request.getCookies();
Cookie lastCookie = null;

if (cookies != null) {
    for (Cookie c : cookies) {
        if (c.getName().equals("last")) {
            lastCookie = c;
            break;
        }
    }
}

if (lastCookie != null) {
    String button = request.getParameter("button");
    if (button != null) {
        current = Integer.parseInt(lastCookie.getValue());
        current = button.equals("next") ? current + 1 : current - 1;
    }
}
response.addCookie(new Cookie("last", String.valueOf(current)));
%>
<%= current %>
<form method="post">
  <input type="submit" name="button" value="prev">
  <input type="submit" name="button" value="next">
</form>
```

---

## 4. HttpSession API (Recommended)

**Mechanism**: Use built-in `HttpSession` object to store state on the server.

### Key Methods:

* `session.setAttribute(String, Object)`
* `session.getAttribute(String)`
* Session auto-created unless `<%@ page session="false" %>`

### Code Snippet:

```jsp
<%
int current = 0;
String last = (String) session.getAttribute("last");

if (last != null) {
    String button = request.getParameter("button");
    if (button != null) {
        current = Integer.parseInt(last);
        current = button.equals("next") ? current + 1 : current - 1;
    }
}

session.setAttribute("last", String.valueOf(current));
%>
<%= current %>
<form method="post">
  <input type="submit" name="button" value="prev">
  <input type="submit" name="button" value="next">
</form>
```

---

### Comparison Table

| Method        | State Stored In  | Visibility to User | Security | Use Case                             |
| ------------- | ---------------- | ------------------ | -------- | ------------------------------------ |
| Hidden Field  | HTML form        | Yes                | Low      | Short forms with small data          |
| URL Rewriting | URL query string | Yes                | Low      | Stateless clients (e.g., no cookies) |
| Cookies       | Client's browser | No (mostly)        | Medium   | Persistent preferences               |
| HttpSession   | Server memory    | No                 | High     | Secure, scalable session management  |

---