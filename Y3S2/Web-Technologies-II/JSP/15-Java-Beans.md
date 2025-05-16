### What is a JavaBean?

* A **reusable** Java class component.
* **Encapsulates business logic**; keeps it separate from presentation logic.
* Requirements:

  1. Public no-argument constructor.
  2. Implements `Serializable` or `Externalizable`.
  3. Properties accessed via **getter/setter** methods using naming conventions.

---

### JavaBean Example – Factorial Logic

```java
package bean;

public class Factorial implements java.io.Serializable {
    int n;

    public void setValue(int v) {
        n = v;
    }

    public int getValue() {
        int prod = 1;
        for (int i = 2; i <= n; i++)
            prod *= i;
        return prod;
    }
}
```

> **Location:** `/WEB-INF/classes/bean/Factorial.java`
> **Compile:**

```bash
javac Factorial.java
```

---

### `<jsp:useBean>` – Instantiate the Bean

```jsp
<jsp:useBean id="fact" class="bean.Factorial" scope="page" />
```

Equivalent to:

```jsp
<% bean.Factorial fact = new bean.Factorial(); %>
```

---

### `<jsp:setProperty>` – Set Bean Property

```jsp
<jsp:setProperty name="fact" property="value" value="5" />
```

Equivalent to:

```jsp
<% fact.setValue(5); %>
```

---

### `<jsp:getProperty>` – Get Bean Property

```jsp
<jsp:getProperty name="fact" property="value" />
```

Equivalent to:

```jsp
<%= fact.getValue() %>
```

---

### Example: Factorial Table Using JSP + JavaBean

```jsp
<jsp:useBean id="fact" class="bean.Factorial" scope="page" />
<table border="1">
  <caption>Factorial table</caption>
  <tr><th>n</th><th>n!</th></tr>

  <%
  for (int i = 2; i < 6; i++) {
  %>
    <jsp:setProperty name="fact" property="value" value="<%=i%>" />
    <tr>
      <td><%=i%></td>
      <td><jsp:getProperty name="fact" property="value" /></td>
    </tr>
  <%
  }
  %>
</table>
```

---