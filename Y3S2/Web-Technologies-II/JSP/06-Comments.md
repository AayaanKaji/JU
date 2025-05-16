### JSP Comments

#### 1. **JSP Comments (`<%-- ... --%>`)**

* Ignored entirely during translation.
* Not added to the generated servlet.
* Not visible in the client response.

**Syntax:**

```jsp
<%-- This is a JSP comment --%>
```

**Example:**

```jsp
<%-- Prints current date and time --%>
<%= new java.util.Date() %>
```

---

### Java Comments (Inside Scriptlets/Declarations)

#### 2. **Single-line (`//`) and Multi-line (`/* ... */`) Comments**

* Used within `<% ... %>` or `<%! ... %>` blocks.
* Added to the servlet's source code, but not to the client response.

**Example:**

```jsp
<%
    // Retrieve all cookies
    Cookie[] cookies = request.getCookies();

    /*
     Check for cookie named "user"
    */
    String user = null;
    for(int i = 0; i < cookies.length; i++)
        if(cookies[i].getName().equals("user"))
            user = cookies[i].getValue();
%>
```

---

### HTML Comments (`<!-- ... -->`)

#### 3. **HTML Comments**

* Included in the servlet and the client response.
* Not rendered visibly in the browser, but visible in page source.

**Example:**

```jsp
<!-- This page was generated at server on
<%= new java.util.Date() %>
-->
```

**Output in response:**

```html
<!-- This page was generated at server on
Sun May 16 2025 11:30:00 GMT
-->
```

---

### Summary

| Type         | Syntax              | Appears in Servlet? | Sent to Client? | Visible in Browser? |
| ------------ | ------------------- | ------------------- | --------------- | ------------------- |
| JSP Comment  | `<%-- ... --%>`     | ✘ No                | ✘ No            | ✘ No                |
| Java Comment | `//` or `/* ... */` | ✔ Yes               | ✘ No            | ✘ No                |
| HTML Comment | `<!-- ... -->`      | ✔ Yes               | ✔ Yes           | ✘ No (view source)  |

---