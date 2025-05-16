### 1. **Instantiated**

* **Definition**: The servlet container creates an instance of the servlet class using its **zero-argument constructor**.
* **When**: This occurs once when the servlet is first loaded (e.g., at container startup if `load-on-startup` is set, or on first request otherwise).

```java
public class MyServlet extends HttpServlet {
    public MyServlet() {
        System.out.println("Servlet instantiated");
    }
}
```

---

### 2. **Initialized**

* **Definition**: The container calls the `init(ServletConfig config)` method after instantiation. This prepares the servlet for request handling.
* **Purpose**: Resource setup, configuration reading, etc.

```java
@Override
public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Servlet initialized");
}
```

---

### 3. **Ready to Service**

* **Definition**: The servlet is fully initialized and is now **capable of handling requests**, though no request is actively being served.

---

### 4. **Servicing**

* **Definition**: This is when the servlet is actively handling a request. The container invokes the `service()` method, which then delegates to `doGet()`, `doPost()`, etc.
* **Threaded**: Multiple requests → multiple threads → concurrent execution.

```java
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    System.out.println("Servicing request");
    resp.getWriter().write("Response from servlet");
}
```

---

### 5. **Not Ready to Service**

* **Definition**: The servlet is temporarily **unavailable** (due to resource issues, admin config, or internal errors).
* **Mechanism**: Can be induced via `UnavailableException`.
* **Behavior**: Container skips sending new requests to the servlet temporarily.

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    throw new UnavailableException("Temporarily unavailable");
}
```

---

### 6. **Destroyed**

* **Definition**: Before removing the servlet from memory, the container calls `destroy()`.
* **Purpose**: Cleanup resources like DB connections, threads, etc.

```java
@Override
public void destroy() {
    System.out.println("Servlet destroyed");
}
```

---

### Summary Diagram (Conceptual)

```
    ┌────────────┐
    │Instantiated│
    └─────┬──────┘
          ↓
    ┌───────────┐
    │Initialized│
    └─────┬─────┘
          ↓
┌──────────────────┐
│ Ready to Service │◄────────────┐
└────┬─────────────┘             │
     ↓                           │
 ┌──────────────┐        ┌───────────────────────┐
 │ Servicing    │───────►│ Temporarily Not Ready │
 └────┬─────────┘        └───────────────────────┘
      ↓
 ┌────────────┐
 │ Destroyed  │
 └────────────┘
```

---

### Note

* Methods: `init()`, `service()`, `doGet()/doPost()`, `destroy()`
* Lifecycle managed by **servlet container** (e.g., Tomcat)
* Multithreaded by default: **stateless design encouraged**
* `@WebServlet` or `web.xml` used for configuration
