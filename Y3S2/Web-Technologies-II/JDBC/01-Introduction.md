### What is JDBC?

* **JDBC**(Java Database Connectivity) is an API in Java to **connect, query, and manipulate databases**.
* Provided by `java.sql` package.
* Enables Java applications (including JSP/Servlets) to interact with:

  * **Relational DBs** (e.g., MySQL, Oracle),
  * **Object-oriented DBs**, and
  * **Object-relational DBs**.

---

### Key JDBC Components

| **Class / Interface** | **Purpose**                                                                                             |
| --------------------- | ------------------------------------------------------------------------------------------------------- |
| **DriverManager**     | Manages JDBC drivers. Used to establish DB connections via `getConnection()` method.                    |
| **Connection**        | Represents a live DB session. Provides methods to create `Statement`, manage transactions, etc.         |
| **Statement**         | Used to execute **static** SQL queries (not parameterized).                                             |
| **PreparedStatement** | Precompiled SQL query with **placeholders** (`?`). Safer and faster for repeated/parameterized queries. |
| **CallableStatement** | Executes **stored procedures** in the database. Supports IN, OUT, and INOUT parameters.                 |
| **ResultSet**         | Stores the result of SELECT queries. Allows row-by-row traversal.                                       |
| **DatabaseMetaData**  | Provides metadata (schema info, DB capabilities, driver details).                                       |
| **ResultSetMetaData** | Provides column-specific metadata about a `ResultSet` (column names, types, etc).                       |
| **SQLException** | handles database access errors |


---

### JDBC Drivers (Types)

| Type | Name                    | Description                                                               |
| ---- | ----------------------- | ------------------------------------------------------------------------- |
| 1    | JDBC-ODBC Bridge        | Translates JDBC calls to ODBC, **platform-dependent**, legacy use only    |
| 2    | Native-API Driver       | Converts JDBC to native DB API, **part Java, part native**, not portable  |
| 3    | Network Protocol Driver | JDBC calls go to middleware server, **pure Java**, good for multi-DB apps |
| 4    | Thin Driver             | **Pure Java**, directly communicates with DB via sockets, most efficient  |
 **Recommended**: Use **Type 4 Driver** for real-world Java DB applications.

---

### JDBC Architectures

| Architecture | Description                     | Used By        |
| ------------ | ------------------------------- | -------------- |
| Two-Tier     | Client ↔ DB Server              | Type 2, Type 4 |
| Three-Tier   | Client ↔ Middleware ↔ DB Server | Type 1, Type 3 |

---

### Basic JDBC Workflow (Using Type 4 Driver)

```java
import java.sql.*;

public class DBExample {
    public static void main(String[] args) throws Exception {
        // 1. Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. Connect to DB
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/dbname", "user", "pass");

        // 3. Create statement
        Statement stmt = con.createStatement();

        // 4. Execute query
        ResultSet rs = stmt.executeQuery("SELECT * FROM students");

        // 5. Process result
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name"));
        }

        // 6. Close resources
        rs.close();
        stmt.close();
        con.close();
    }
}
```

---

### Summary Table

| Feature             | Type 1       | Type 2 | Type 3        | Type 4     |
| ------------------- | ------------ | ------ | ------------- | ---------- |
| Pure Java           | ❌            | ❌      | ✅            | ✅          |
| Requires Native Lib | ✅           | ✅      | ❌             | ❌          |
| Platform Dependent  | ✅           | ✅      | ❌             | ❌          |
| Performance         | Low          | Medium | Medium        | High       |
| Usage               | Testing only | Legacy | Multi-DB Apps | Production |

---