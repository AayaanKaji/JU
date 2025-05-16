## **Server-Side Include (SSI): Overview**

**SSI** allows embedding **dynamic content** (e.g., servlet output, timestamps, environment variables) into static `.shtml` HTML pages.

### Key Points:

* Used in `.shtml` files.
* Tomcat uses `org.apache.catalina.ssi.SSIServlet` to process SSI directives.
* Useful for **mixing static HTML and dynamic server content**.

---

## **Tomcat Configuration Steps**

1. **Enable the SSI Servlet**:

   * Edit `$TOMCAT_HOME/conf/web.xml`
   * Uncomment `<servlet>` and `<servlet-mapping>` entries for `SSIServlet`.

2. **Set Privileged Context**:

   * Modify `$TOMCAT_HOME/conf/context.xml`:

     ```xml
     <Context privileged="true">
       ...
       <WatchedResource>WEB-INF/web.xml</WatchedResource>
     </Context>
     ```

3. **Use `.shtml` extension** for your HTML files.

---

## **SSI Directive Syntax**

```html
<! -- #directive attribute="value" attribute="value" -- >
```

---

## 🔧 **Common SSI Directives**

| Directive                  | Description                                  | Example                                         |
| -------------------------- | -------------------------------------------- | ----------------------------------------------- |
| `echo`                     | Inserts value of a variable                  | `<!--#echo var="DATE_LOCAL" -->`                |
| `printenv`                 | Lists all environment variables              | `<!--#printenv -->`                             |
| `config`                   | Sets formatting options (e.g., time format)  | `<!--#config timefmt="%A %B %d, %Y" -->`        |
| `fsize`                    | Prints file size                             | `<!--#fsize file="SSI.shtml" -->`               |
| `flastmod`                 | Prints last modified time of a file          | `<!--#flastmod file="SSI.shtml" -->`            |
| `include`                  | Includes output from another file or servlet | `<!--#include virtual="servlet/HelloWorld" -->` |
| `set`                      | Creates/sets a variable                      | `<!--#set var="sum" value="0" -->`              |
| `if / elif / else / endif` | Conditional logic                            | See below ⬇️                                    |

---

## **Conditional SSI Logic Example**

```html
<!--#config timefmt="%A" -->

<!--#if expr="$DATE_LOCAL = /Sunday/" -->
  <p>Today is Sunday</p>
<!--#elif expr="$DATE_LOCAL = /Saturday/" -->
  <p>Today is Saturday</p>
<!--#else -->
  <p>Week day</p>
<!--#endif -->
```

---

## **Including a Servlet Output**

Place the following line in `SSI.shtml`:

```html
<!--#include virtual="servlet/HelloWorld" -->
```

Assuming `HelloWorld` is a deployed servlet, its output will be **embedded dynamically**.

---

## **Essentials**

* SSI works only with `.shtml` files.
* Tomcat uses a servlet (`SSIServlet`) to process SSI.
* SSI enables simple dynamic behavior without full JSP/Servlet development.
* Common for **including servlets**, dynamic timestamps, or displaying file metadata.
* Not a replacement for JSP/Servlet logic — just a lightweight inclusion mechanism.

---