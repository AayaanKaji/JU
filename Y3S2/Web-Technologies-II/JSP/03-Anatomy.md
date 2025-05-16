### Structure of a JSP Page

A JSP page is composed of:

1. **Template Text**

   * Static content: HTML or XML markup.
   * Rendered directly to the response as-is.

2. **JSP Constructs**

   * Dynamic content embedded in the page.
   * Three main types:

     * **Scripting Elements**
     * **Directives**
     * **Actions**

---

### Scripting Elements

Used to embed Java code directly into the JSP. There are three types:

1. **Scriptlets**
   Syntax: `<% Java code %>`

   * Can include loops, conditionals, and API calls.
   * Executed as part of `_jspService()` method.

   Example:

   ```jsp
   <% for (int i = 0; i < 5; i++) { %>
       <p>Line <%= i %></p>
   <% } %>
   ```

2. **Declarations**
   Syntax: `<%! Java declaration %>`

   * Declares fields and methods.
   * Code is placed at class level in the generated servlet.

   Example:

   ```jsp
   <%! int counter = 0; %>
   <%! public int increment() { return ++counter; } %>
   ```

3. **Expressions**
   Syntax: `<%= Java expression %>`

   * Outputs the result of an expression directly into the response.

   Example:

   ```jsp
   Current time: <%= new java.util.Date() %>
   ```

---

### Directives

Provide metadata and control how the JSP is translated into a servlet.

Common directive: `page`

Syntax:

```jsp
<%@ page attribute="value" %>
```

Example:

```jsp
<%@ page import="java.util.Date" %>
```

---

### Actions

XML-like tags that use built-in functionality or JavaBeans.

Example: Using a JavaBean

```jsp
<jsp:useBean id="user" class="com.example.User" scope="session" />
<jsp:setProperty name="user" property="name" value="Alice" />
<jsp:getProperty name="user" property="name" />
```

---

### JSP Syntax Rules

* All tags must have matching end tags (if applicable).
* Attribute values must be quoted.
* Special characters:

  * `%` → use `\%`
  * `\` → use `\\`
* Whitespace is preserved during translation.

---

### Summary of Components

| Component Type    | Purpose                                   |
| ----------------- | ----------------------------------------- |
| **Template Text** | Static HTML/XML                           |
| **Scriptlets**    | Java code for logic                       |
| **Declarations**  | Variable/method declarations              |
| **Expressions**   | Output of Java expressions                |
| **Directives**    | Configure page behavior and imports       |
| **Actions**       | Use components and control page execution |

---