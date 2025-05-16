### JSP Declarations

**Purpose:**
To declare variables, methods, or inner classes at the servlet class level, making them available throughout the JSP page.

**Syntax:**

```jsp
<%! 
   // variable or method declarations
%>
```

---

### Behavior

* Declarations are placed outside the `_jspService()` method as instance variables or methods of the generated servlet class.
* Variables declared here persist for the lifetime of the servlet instance (shared across requests if `isThreadSafe` is true).
* Declarations have a broader scope than scriptlet variables (which are local to `_jspService()`).

---

### Examples

* Declaring variables:

```jsp
<%! int sum = 0; %>
<%! int x, y, z; %>
<%! java.util.Hashtable table = new java.util.Hashtable(); %>
```

* Instance variable example:

```jsp
<%! java.util.Date lastLoaded = new java.util.Date(); %>
The servlet was last loaded on <b><%= lastLoaded %></b>
```

Output:

```
The servlet was last loaded on Sun Sep 22 04:19:12 PDT 2013
```

* Variables declared here can be accessed in scriptlets and expressions.

---

### XML Equivalent

```jsp
<jsp:declaration>
  // declarations
</jsp:declaration>
```

---

### Summary

| Feature              | Description                                                      |
| -------------------- | ---------------------------------------------------------------- |
| Use                  | Declare variables, methods, inner classes at servlet class level |
| Syntax               | `<%! declarations %>`                                            |
| Scope                | Instance-level (accessible across requests if thread safe)       |
| Variables visibility | Available to scriptlets and expressions                          |
| XML Equivalent       | `<jsp:declaration> declarations </jsp:declaration>`              |

---