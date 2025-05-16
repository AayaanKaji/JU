### **1. What is JSP?**

JSP (JavaServer Pages) is a **high-level abstraction** built on top of the **Java Servlet API**.

* Allows **Java code** to be embedded inside **HTML**.
* Aimed at simplifying the creation of **dynamic web content**.
* Must be executed inside a **web container** (like Apache Tomcat).

---

### **2. JSP Compilation Lifecycle**

The JSP file is not executed directly. Instead:

#### Translation Process (if JSP is new or updated):

1. Translates `.jsp` → `.java` (a servlet class).
2. Compiles `.java` → `.class`.
3. Loads servlet class into JVM.
4. Calls lifecycle methods:

```java
public void jspInit() {
    // Initialization logic here
}

public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Main processing logic
}
```

#### If JSP is unchanged:

* Uses cached **servlet instance** directly.

---

### **3. Lifecycle Overview (Comparison with Servlet)**

| JSP Phase      | Equivalent Servlet Method |
| -------------- | ------------------------- |
| Page load      | `jspInit()`               |
| Client request | `_jspService()`           |
| Page unload    | `jspDestroy()`            |

---

### **4. How JSP Handles HTTP Requests**

JSPs support **HTTP** natively, but are fundamentally **servlets**.

When a client sends a request like `http://127.0.0.1:8080/hello.jsp`:

* Web server checks `.jsp` extension.
* Forwards to **JSP Engine** (a specialized servlet).
* Engine checks timestamps:

  * If servlet doesn't exist or is outdated: Translate & compile.
  * Else: Reuse servlet instance.

---

### **5. JSP Code Example – Login Snippet**

```jsp
<%@ page import="java.sql.*" %>
<%
    String user = request.getParameter("username");
    String pass = request.getParameter("password");

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "password");

        stmt = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
        stmt.setString(1, user);
        stmt.setString(2, pass);

        rs = stmt.executeQuery();

        if (rs.next()) {
            out.println("Login successful!");
        } else {
            out.println("Invalid credentials.");
        }
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    } finally {
        if (rs != null) rs.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
    }
%>
```

> **Best Practices:** JSP should ideally not contain business logic—use **JSTL**, **Servlets**, or **MVC frameworks** (like Spring MVC).

---

### **6. JSP Engine Examples**

* **Apache Tomcat** (default port: `8080`)
* **Java Web Server**
* **WebLogic**
* **WebSphere**

---

### **7. Advantages of JSP**

* Faster development due to automatic compilation.
* Hot reloading via modification-time checks.
* Full access to Java API.
* Secure and scalable.
* Portable across platforms.

---

### **8. Extending Beyond HTTP**

Though designed for **HTTP**, JSP and servlets can theoretically be extended for other protocols (e.g., FTP, SMTP) via custom implementations, though this is **non-standard**.

---