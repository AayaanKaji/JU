## Standard JSP Action Tags

**Standard action tags** in JSP are **XML-based tags** that enable various dynamic behaviors such as including resources, forwarding requests, managing JavaBeans, or integrating plugins. Unlike directives, standard actions are processed at **runtime**, not at **translation/compilation time**.

---

### 1. `<jsp:include>`

**Purpose:**
Dynamically includes the output of another resource (e.g., `.jsp`, `.html`, `.servlet`) into the current JSP **at request time**.

**Syntax:**

```jsp
<jsp:include page="relativeURL | <%= expression %>" flush="true" />
```

**Example:**

```jsp
<jsp:include page="header.jsp" />
```

**Key Characteristics:**

* Content is included at **runtime**, not compile time.
* Compared to the directive `<%@ include file="..." %>`, which performs **static inclusion**, `<jsp:include>` is **dynamic**.
* Commonly used for modularizing pages (e.g., including headers, footers, navigation bars).

---

### 2. `<jsp:param>`

**Purpose:**
Adds parameters to the request when using `<jsp:include>` or `<jsp:forward>`.

**Syntax:**

```jsp
<jsp:param name="parameterName" value="literal | <%= expression %>" />
```

**Usage with `<jsp:include>`:**

```jsp
<jsp:include page="process.jsp">
  <jsp:param name="user" value="<%= user %>" />
  <jsp:param name="sessionId" value="<%= System.currentTimeMillis() %>" />
</jsp:include>
```

**Constraints:**

* The `name` attribute must be **static** (i.e., cannot use `<%= ... %>`).
* The `value` attribute can be dynamic.

---

### 3. `<jsp:forward>`

**Purpose:**
Transfers request processing **internally** to another resource. Any content generated prior to the forward is **discarded**.

**Syntax:**

```jsp
<jsp:forward page="relativeURL | <%= expression %>" />
```

**Example:**

```jsp
<jsp:forward page="date.jsp" />
```

**With Parameters:**

```jsp
<jsp:forward page="<%= mailbox + ".jsp" %>">
  <jsp:param name="user" value="<%= user %>" />
</jsp:forward>
```

**Key Characteristics:**

* Happens **on the server side**; client is unaware of the internal dispatch.
* Control is **transferred**, not shared — processing of the current page **stops** after the forward.

---

### 4. `<jsp:plugin>`

**Purpose:**
Generates an HTML `<object>` or `<embed>` tag to **dynamically download a Java plugin** and load an applet or JavaBean.

**Syntax:**

```jsp
<jsp:plugin type="applet" code="AppletClassName">
  <jsp:params>
    <jsp:param name="param1" value="value1" />
  </jsp:params>
  <jsp:fallback>
    <p> Plugin not supported. </p>
  </jsp:fallback>
</jsp:plugin>
```

**Example:**

```jsp
<jsp:plugin type="applet" code="Message">
  <jsp:params>
    <jsp:param name="message" value="Hello World!" />
  </jsp:params>
  <jsp:fallback>
    <p>Unable to start Plug-in.</p>
  </jsp:fallback>
</jsp:plugin>
```

**Notes:**

* Rarely used in modern JSP development due to the obsolescence of Java applets and plugin technologies.
* The `<jsp:fallback>` provides alternative content if the plugin cannot be loaded.

---