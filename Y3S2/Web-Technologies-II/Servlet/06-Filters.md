## **Definition of Filters**

A **Filter** in Java EE is an object that intercepts **HTTP requests and responses**. It is positioned **between the client and servlet**, and it provides a mechanism for transforming or using the request or response **before it reaches the servlet or before the response reaches the client**.

---

## **Filter Life Cycle**

A filter follows a well-defined life cycle similar to a servlet:

| Method                                                                 | Purpose                                                                      |
| ---------------------------------------------------------------------- | ---------------------------------------------------------------------------- |
| `init(FilterConfig config)`                                            | Called once when the filter is instantiated. Initialization logic goes here. |
| `doFilter(ServletRequest req, ServletResponse res, FilterChain chain)` | Called for each request. Core filtering logic resides here.                  |
| `destroy()`                                                            | Called once before the filter is removed. Cleanup logic goes here.           |

---

## **Filter Invocation Chain**

* Filters are executed in the order they are declared.
* The filter must call `chain.doFilter(request, response)` to forward the request to the next filter or target resource (servlet).
* If it **does not** call `chain.doFilter()`, the chain **terminates**, and the request is not processed further.

---

## **Common Use Cases**

Filters are **pre-processing** and **post-processing** tools for:

* Authentication and Authorization
* Logging and Auditing
* Compression (e.g., GZIP)
* Encryption/Decryption
* Token validation
* Image/Data transformation
* Request/Response modification
* Request rejection (firewall-like behavior)

---

## **Implementing a Filter**

Filters must implement the `javax.servlet.Filter` interface

### Minimal Structure:

```java
import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    public void init(FilterConfig config) { /* Initialization logic */ }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // Pre-processing
        chain.doFilter(request, response); // Forward to next filter or servlet
        // Post-processing
    }

    public void destroy() { /* Cleanup logic */ }
}
```

---

## **Example: Firewall Filter**

The following filter **allows** requests only from IP `172.16.4.248`:

```java
import java.io.*;
import javax.servlet.*;

public class Firewall implements Filter {
    private FilterConfig config = null;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (request.getRemoteAddr().equals("172.16.4.248")) {
            chain.doFilter(request, response); // Forward if IP matches
        }
        // Else: silently drop or optionally send error
    }

    public void destroy() {
        config = null;
    }
}
```

### Behavior:

* If the incoming request is **from the allowed IP**, it's passed to the next component.
* Otherwise, the request is silently **filtered out**.

---

## **Deployment (web.xml or @WebFilter)**

### Using `web.xml`:

```xml
<filter>
    <filter-name>Firewall</filter-name>
    <filter-class>Firewall</filter-class>
</filter>
<filter-mapping>
    <filter-name>Firewall</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

### Using Annotation:

```java
@WebFilter("/*")
public class Firewall implements Filter { ... }
```

---

## **Key Points**

* `FilterChain` controls the chain progression.
* Filters are applied **in declaration order**.
* `FilterConfig` provides access to init parameters and `ServletContext`.
* Filters **do not generate responses**, but they can **modify or block** them.

---