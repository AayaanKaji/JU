### JDBC: Basic Steps for Database Interaction

The **Java Database Connectivity (JDBC)** API provides a standard interface for Java applications to interact with databases. The process follows a systematic sequence of steps:

---

## 1. **Loading a JDBC Driver**

Initialize the JDBC driver so it can be registered with `DriverManager`.

### Method 1: `Class.forName()` (Preferred)

```java
Class.forName("com.mysql.cj.jdbc.Driver");   // For MySQL 8+
Class.forName("oracle.jdbc.driver.OracleDriver"); // For Oracle
```

> ⚠️ This relies on the driver class having a static initializer that registers itself with `DriverManager`.

### Method 2: Explicit registration

```java
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
```

> Useful if auto-registration via `Class.forName()` fails.

### Driver Location

* In **web apps (e.g., Tomcat)**: Place `.jar` (e.g., `mysql-connector-java-8.x.x.jar`) in `TOMCAT_HOME/lib` or in `ROOT/web-apps/lib`.
* In **standalone apps**: Add the JAR to the `CLASSPATH`.

---

## 2. **Establishing a Database Connection**

The `DriverManager.getConnection()` method establishes a connection and returns a `Connection` object.

### Format for `getConnection`:

```java
public static Connection getConnection(String url, String user, String password);
public static Connection getConnection(String url);
public static Connection getConnection(String url, Properties info);
```

### MySQL URL Format:

```
jdbc:mysql://[host]:[port]/[database]
```

#### Example (MySQL)

```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/test", "root", "root");
```

#### Example (Oracle)

```java
Connection con = DriverManager.getConnection(
    "jdbc:oracle:thin:@miroracle:1521:mirora", "root", "root");
```

---

## 3. **Using URL Parameters**

Instead of passing username/password separately:

```java
String url = "jdbc:mysql://localhost:3306/test?user=root&password=root";
Connection con = DriverManager.getConnection(url);
```

---

## 4. **Using `Properties` Object (Alternative)**

Allows passing multiple config values in a clean, object-oriented way:

```java
String url = "jdbc:mysql://localhost:3306/test";

Properties props = new Properties();
props.setProperty("user", "root");
props.setProperty("password", "root");

Connection con = DriverManager.getConnection(url, props);
```

---

## 5. **The `Connection` Object**

The `Connection` interface provides:

* Creating `Statement`, `PreparedStatement`, `CallableStatement`
* Managing transactions: `commit()`, `rollback()`, `setAutoCommit()`
* Accessing metadata: `getMetaData()`
* Closing connections: `close()`

---

### Recap Table

| **Step**             | **Method/Code**                                             |
| -------------------- | ----------------------------------------------------------- |
| Load Driver          | `Class.forName(...)` or `DriverManager.registerDriver(...)` |
| Establish Connection | `DriverManager.getConnection(...)`                          |
| URL Format (MySQL)   | `jdbc:mysql://host:port/dbname`                             |
| URL Format (Oracle)  | `jdbc:oracle:thin:@host:port:sid`                           |
| Use `Properties`     | `Properties p = new Properties(); ...`                      |

---