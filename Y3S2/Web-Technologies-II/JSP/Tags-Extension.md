## JSP Tag Extensions

**Tag Extensions** in JSP allow developers to define **custom XML-like tags** that encapsulate reusable functionality, typically implemented in Java classes (tag handlers). These are more powerful and flexible than JavaBeans and better suited for complex presentation logic, including:

* Attribute handling
* Nested tag structures
* Iteration over content
* Defining scripting variables
* Cooperative tag behavior

---

## 1. **Types of Custom Tags**

| **Tag Type**                      | **Example**                                       | **Description**                                  |
| --------------------------------- | ------------------------------------------------- | ------------------------------------------------ |
| Simple Tags                       | `<user:copyright>`                                 | No body, no attributes                           |
| Tags with Attributes              | `<user:hello name="Monali" />`                     | Attributes but no body                           |
| Tags with Body Content            | `<user:hello> <%= name %> </user:hello>`            | Contains text, scripting elements, or other tags |
| Tags Defining Scripting Variables | `<user:animation id="logo" />`                     | Declares variables for use within the JSP page   |
| Cooperative Tags                  | `<user:tag1 id="obj1" /> <user:tag2 name="obj1" />` | Multiple tags share a named object and interact  |

---

## 2. **Steps to Create a Custom Tag**

### Step 1: **Create the Tag Handler Class**

* Custom tag handlers must extend `SimpleTagSupport` (for simple tags) or implement `TagSupport`/`BodyTagSupport` for more complex use-cases.
* Example: **HelloTag.java**

```java
package tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class HelloTag extends SimpleTagSupport {
    String name = "World!";

    public void setName(String name) {
        this.name = name;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        out.println("Hello " + name);
    }
}
```

Compile using:

```bash
javac -classpath <path_to_jsp-api.jar> HelloTag.java
```

Place the `.class` file under:
`/WEB-INF/classes/tag/HelloTag.class`

---

### Step 2: **Create the TLD File (Tag Library Descriptor)**

* File: `tags.tld`
* Location: `/net/taglib/tags.tld` (if web app root is `/net`)

```xml
<?xml version="1.0"?>
<taglib>
  <tlib-version>1.0</tlib-version>
  <jsp-version>1.2</jsp-version>
  <short-name>Simple tag library</short-name>

  <tag>
    <description>Prints Hello 'name'</description>
    <name>hello</name>
    <tag-class>tag.HelloTag</tag-class>
    <body-content>empty</body-content>
    <attribute>
      <name>name</name>
      <required>false</required>
      <rtexprvalue>true</rtexprvalue>
    </attribute>
  </tag>
</taglib>
```

---

### Step 3: **Use the Tag in JSP**

**Taglib Directive:**

```jsp
<%@ taglib prefix="user" uri="/taglib/tags.tld" %>
```

**Usage Examples:**

```jsp
<user:hello name="Monali" />         // Prints: Hello Monali
<user:hello />                       // Prints: Hello World!
<user:hello name="<%=name%>" />     // Prints: Hello Tom (if name = "Tom")
```

---

## 3. **Custom Tag for Iteration – `<user:FactTable>`**

This tag evaluates the factorial of numbers in a given range.

### Handler: `FactorialTag.java`

```java
package tag;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class FactorialTag extends SimpleTagSupport {
    int start, end;

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void doTag() throws JspException, IOException {
        for (int i = start; i <= end; i++) {
            int prod = 1;
            for (int j = 1; j <= i; j++) prod *= j;

            getJspContext().setAttribute("count", String.valueOf(i));
            getJspContext().setAttribute("fact", String.valueOf(prod));

            getJspBody().invoke(null);  // Executes body content
        }
    }
}
```

### JSP Usage:

```jsp
<%@ taglib prefix="user" uri="/taglib/tags.tld" %>

<user:FactTable start="2" end="6">
  ${count}! = ${fact} <br>
</user:FactTable>
```

Output:

```
2! = 2
3! = 6
4! = 24
5! = 120
6! = 720
```

---

## 4. **Alternate: Simple Fact Tag without Body**

### `FactTag.java`:

```java
public class FactTag extends SimpleTagSupport {
    int no;

    public void setNo(int no) {
        this.no = no;
    }

    public void doTag() throws JspException, IOException {
        int prod = 1;
        for (int j = 1; j <= no; j++) prod *= j;

        JspWriter out = getJspContext().getOut();
        out.println(prod);
    }
}
```

### JSP Usage:

```jsp
<% for(int i = 2; i <= 6; i++) { %>
  <%= i %>! = <user:fact no="<%=i%>" /><br>
<% } %>
```

---

## Summary: Key Components in Tag Development

| **Component**          | **Purpose**                                             |
| ---------------------- | ------------------------------------------------------- |
| Tag Handler Class      | Java class extending `SimpleTagSupport`                 |
| Tag Library Descriptor | XML file mapping tag names to handler classes (`*.tld`) |
| Taglib Directive       | Declares tag library in JSP via `prefix` and `uri`      |
| Custom Tag Use         | Tag invocation with optional attributes and body        |

---