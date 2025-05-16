## Synchronization in JSP

### Problem Overview

When you declare variables using **JSP declarations** (`<%! ... %>`), they are compiled into **instance variables** of the generated servlet class. Since servlets are **multithreaded by default**, multiple client requests may execute concurrently using the same instance of the servlet. Consequently, **instance variables are shared across threads**, which can lead to **race conditions** and **inconsistent behavior** if not properly synchronized.

---

### Example of Unsafe Access

```jsp
<%! int n = 1; %>

<%
for (int i = 0; i < 5; i++) {
    out.println("Next integer: " + n++ + "<br>");
    Thread.sleep(500);
}
%>
```

**Expected behavior:** Print five consecutive integers.
**Actual behavior:** Concurrent requests may interfere, leading to interleaved or skipped values due to unsynchronized access to `n`.

---

### Solution 1: Explicit Synchronization

Synchronize access to the shared resource using a mutually exclusive block.

```jsp
<%! 
int n = 1; 
Object lock = new Object(); 
%>

<%
synchronized(lock) {
    for (int i = 0; i < 5; i++) {
        out.println("Next integer: " + n++ + "<br>");
        Thread.sleep(500);
    }
}
%>
```

**Explanation:**

* `lock` is a private object used to ensure mutual exclusion.
* Only one thread at a time can enter the `synchronized` block, preventing race conditions.

---

### Solution 2: Disable Multithreading for the Page

Declare the page as **not thread-safe**:

```jsp
<%@ page isThreadSafe="false" %>

<%! int n = 1; %>

<%
for (int i = 0; i < 5; i++) {
    out.println("Next integer: " + n++ + "<br>");
    Thread.sleep(500);
}
%>
```

**Effect:**

* The JSP container ensures that **only one thread executes this page at a time**.
* Internally, this means that the container may **serialize access** to the servlet or **instantiate a new servlet** per request (implementation-dependent).

**Caution:**

* Disabling thread safety may hurt scalability and performance.
* It is generally preferable to handle synchronization **manually and selectively** instead of using `isThreadSafe="false"`.

---

### Best Practices

* **Avoid using instance variables** in JSPs unless necessary.
* Prefer **request-scoped local variables** inside scriptlets.
* If shared mutable state is needed, **synchronize properly** or **store state externally** (e.g., in the session or application context with appropriate locking).
* Consider using **Servlets or MVC frameworks (e.g., Spring MVC)** where synchronization and scope management are more controlled and testable.

---