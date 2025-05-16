## JDBC Statement Execution: Core Concepts

### 1. **Creating a Statement**

```java
Statement stmt = con.createStatement();
```

Used for executing **static SQL statements** (i.e., statements without parameters).

---

### 2. **Executing DDL/DML Statements: `executeUpdate()`**

```java
String create = "CREATE TABLE accounts (accNum INT PRIMARY KEY, holderName VARCHAR(20), balance INT)";
stmt.executeUpdate(create);
```

* Suitable for: `CREATE`, `INSERT`, `UPDATE`, `DELETE`
* Returns: `int` (number of rows affected)
* Auto-committed by default

---

### 3. **Executing Queries: `executeQuery()`**

```java
String query = "SELECT * FROM accounts";
ResultSet rs = stmt.executeQuery(query);

while (rs.next()) {
    System.out.println(rs.getInt("accNum"));
    System.out.println(rs.getString("holderName"));
    System.out.println(rs.getInt("balance"));
}
```

* Suitable for: **SELECT** queries
* Returns: `ResultSet` object
* Use `rs.getX()` (e.g., `getInt()`, `getString()`) to read column values

---

### 4. **Generic Execution: `execute()`**

```java
boolean hasResultSet = stmt.execute("some SQL statement");

if (hasResultSet) {
    ResultSet rs = stmt.getResultSet();
    // process result set
} else {
    int count = stmt.getUpdateCount();
    // process update count
}
```

* Use when statement type is **unknown at runtime**
* Returns: `true` if result is a `ResultSet`; `false` if it is an update count

---

## Transactions with JDBC

By default, each statement is committed immediately. For **atomic transactions**, disable auto-commit:

```java
con.setAutoCommit(false);

stmt.executeUpdate(...); // multiple DML statements
stmt.executeUpdate(...);
con.commit();
```

> Use `con.rollback()` to revert on failure.

---

## Batch Updates

Useful when executing many similar DML statements:

```java
stmt.clearBatch();
stmt.addBatch("INSERT INTO accounts VALUES (3, 'Alice', 15000)");
stmt.addBatch("INSERT INTO accounts VALUES (4, 'Bob', 17000)");
stmt.executeBatch();
```

* Statements are executed **in order**
* More efficient than executing one-by-one

---

## PreparedStatement: Pre-compiled and Parameterized

### Benefits:

* Precompiled once → faster repeated execution
* Handles **user-supplied data safely** (prevents SQL injection)
* Escapes characters (e.g., `'` inside strings)

```java
PreparedStatement ps = con.prepareStatement("INSERT INTO questions VALUES (?, ?)");

ps.setInt(1, 3);
ps.setString(2, "What's JDBC?");
ps.executeUpdate();
```

---

## Example: Reading from File

```java
PreparedStatement ps = con.prepareStatement("INSERT INTO questions VALUES (?, ?)");

BufferedReader br = new BufferedReader(new FileReader("question.txt"));
String line;
while ((line = br.readLine()) != null) {
    String[] parts = line.split(":");
    ps.setInt(1, Integer.parseInt(parts[0]));
    ps.setString(2, parts[1]);
    ps.executeUpdate();
}
```

> Safe even if the question contains quotes or special characters.

---

## CallableStatement: Calling Stored Procedures

Used for **calling stored procedures** in the database:

```java
String sql = "{call changePassword(?, ?, ?)}";
CallableStatement cs = con.prepareCall(sql);
cs.setString(1, "username");
cs.setString(2, "oldPass");
cs.setString(3, "newPass");
cs.execute();
```

* `prepareCall()` is used instead of `prepareStatement()`
* Supports input, output, and INOUT parameters (via `registerOutParameter()`)

---

### Summary Table

| Interface           | Method               | Use Case                          | Return Type                      |
| ------------------- | -------------------- | --------------------------------- | -------------------------------- |
| `Statement`         | `executeUpdate()`    | DDL/DML                           | `int` (rows affected)            |
|                     | `executeQuery()`     | DQL (SELECT)                      | `ResultSet`                      |
|                     | `execute()`          | Mixed/Unknown                     | `boolean` + `ResultSet` or `int` |
| `PreparedStatement` | `prepareStatement()` | Repeated execution, param queries | `PreparedStatement`              |
| `CallableStatement` | `prepareCall()`      | Stored Procedures                 | `CallableStatement`              |

---