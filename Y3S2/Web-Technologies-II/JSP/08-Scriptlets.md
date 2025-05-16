### JSP Scriptlets

**Purpose:**
To insert arbitrary Java code blocks inside a JSP page for processing logic, variable declarations, or method calls.

**Syntax:**

```jsp
<% 
   // Java code here 
%>
```

---

### Behavior

* The Java code inside `<% ... %>` is copied directly into the `_jspService()` method of the generated servlet.
* Scriptlets allow embedding any valid Java statements, including declarations, loops, conditionals, and method calls.
* Output generated using `out.print()` or `out.println()` inside scriptlets appears in the client response.

---

### Examples

* Display current date and time:

```jsp
<%
  java.util.Date d = new java.util.Date();
  out.println("Date and time is : " + d);
%>
```

Output:

```
Date and time is : Sun Sep 22 04:15:50 PDT 2013
```

* Conditional logic example (even or odd number):

```jsp
<%
  int no = (int)(Math.random() * 10);
  if(no % 2 == 0) {
%>
Even
<% } else { %>
Odd
<% } %>
```

This generates servlet code similar to:

```java
int no = (int)(Math.random() * 10);
if (no % 2 == 0) {
  out.write("\r\n");
  out.write("Even\r\n");
} else {
  out.write("\r\n");
  out.write("Odd\r\n");
}
```

---

### XML Equivalent

```jsp
<jsp:scriptlet>
  // Java code here
</jsp:scriptlet>
```

---

### Summary

| Feature            | Description                                            |
| ------------------ | ------------------------------------------------------ |
| Use                | Insert arbitrary Java code in JSP                      |
| Syntax             | `<% Java code %>`                                      |
| Output Control     | Use `out.print()` or `out.println()` for output        |
| Control Structures | Conditionals, loops, and other Java constructs allowed |
| XML Equivalent     | `<jsp:scriptlet> Java code </jsp:scriptlet>`           |

---