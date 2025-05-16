### Scope of JSP Objects

**Definition:**
Scope defines the accessibility and lifetime of JSP objects within a web application.

---

### Four JSP Object Scopes

| Scope           | Description                                                               | Examples (Implicit Objects)                                     |
| --------------- | ------------------------------------------------------------------------- | --------------------------------------------------------------- |
| **page**        | Accessible only within the same JSP page where created.                   | `out`, `exception`, `response`, `pageContext`, `config`, `page` |
| **request**     | Accessible across multiple JSP pages handling the same HTTP request.      | `request`                                                       |
| **session**     | Accessible to all JSP pages during a single user session.                 | `session`                                                       |
| **application** | Accessible to all JSP pages and servlets within the same web application. | `application`                                                   |

---

### Details

* **Page scope:**
  Limited to the current JSP page. JSP objects created via `<jsp:useBean>` default to this scope.

* **Request scope:**
  Shares objects among all resources that process the same client request, useful for forwarding or including other pages.

* **Session scope:**
  Objects persist across multiple requests from the same user session.

* **Application scope:**
  Objects are shared across the entire web application and all users.

---

### Summary

* Choose scope based on the object lifetime and visibility needed.
* Implicit JSP objects provide predefined scoped variables for common use.

---