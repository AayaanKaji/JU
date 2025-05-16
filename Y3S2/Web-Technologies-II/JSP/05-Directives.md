### JSP Directives

**Purpose:**
Provide instructions to the JSP container on how to process the page.
**Syntax:**

```jsp
<%@ directiveName attribute="value" ... %>
```

**Common Directives:**

1. `page` – Defines page-specific settings
2. `include` – Statically includes external file content
3. `taglib` – Declares usage of custom tag libraries

---

### 1. `<%@ page ... %>` — Page Directive

**Used to:**

* Import classes
* Control buffering, session, threading, content type, etc.

#### Common Attributes:

| Attribute      | Purpose                                                                          |          |
| -------------- | -------------------------------------------------------------------------------- | -------- |
| `import`       | Imports Java classes (`java.io.*, java.util.Vector`, etc.)                       |          |
| `session`      | Enables/disables use of `session` object (`true` by default)                     |          |
| `buffer`       | Sets response buffer size (e.g., `buffer="16kb"` or `buffer="none"`)             |          |
| `autoFlush`    | Flush buffer automatically when full (`true` by default)                         |          |
| `isThreadSafe` | Controls concurrent access (`true` allows multithreading)                        |          |
| `info`         | Metadata about the page; accessed via `getServletInfo()`                         |          |
| `contentType`  | Sets MIME type and character encoding (default: `text/html; charset=ISO-8859-1`) |          |
| `errorPage`    | Path to error-handling JSP (e.g., `errorPage="error.jsp"`)                       |          |
| `isErrorPage`  | Marks this page as capable of handling errors (\`true                            | false\`) |

#### Examples:

```jsp
<%@ page import="java.util.*, java.io.*" %>
<%@ page session="false" %>
<%@ page buffer="16kb" autoFlush="true" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page errorPage="error.jsp" isErrorPage="false" %>
```

---

### 2. `<%@ include file="..." %>` — Include Directive

**Purpose:**
Statically includes the contents of the specified file during the translation phase.

* File can be `.html`, `.jsp`, or `.txt`
* Equivalent to copy-pasting the content

**Syntax:**

```jsp
<%@ include file="relativeURL" %>
```

**Examples:**

```jsp
<%@ include file="header.html" %>
<%@ include file="login.jsp" %>
```

---

### Summary Table

| Directive | Description                                        |
| --------- | -------------------------------------------------- |
| `page`    | Controls translation and runtime behavior of JSP   |
| `include` | Inserts content at translation time                |
| `taglib`  | Declares custom tag librariesr |

---