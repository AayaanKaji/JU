## What is a Stored Procedure?

A **stored procedure** is a precompiled block of SQL code (optionally with procedural logic) stored in the database under a specific name.

### Features:

* Accepts **input**, **output**, and **INOUT** parameters.
* Executes complex business logic close to the data.
* Reduces network round-trips (compared to sending multiple SQL statements).
* Can be reused by multiple applications.

---

## Syntax: Stored Procedure (in SQL)

Example in **Oracle**:

```sql
CREATE OR REPLACE PROCEDURE changePassword (
    username IN VARCHAR,
    oldPass IN VARCHAR,
    newPass IN VARCHAR
)
AS
BEGIN
    UPDATE users
    SET password = newPass
    WHERE uname = username AND password = oldPass;
END;
```

Example in **MySQL**:

```sql
DELIMITER //

CREATE PROCEDURE changePassword (
    IN username VARCHAR(50),
    IN oldPass VARCHAR(50),
    IN newPass VARCHAR(50)
)
BEGIN
    UPDATE users
    SET password = newPass
    WHERE uname = username AND password = oldPass;
END //

DELIMITER ;
```

---

## CallableStatement in JDBC

To call a stored procedure, use the **`CallableStatement`** interface.

### 1. **Basic Stored Procedure Call**

#### Syntax:

```java
CallableStatement cs = con.prepareCall("{call changePassword(?, ?, ?)}");
cs.setString(1, "john");          // IN parameter
cs.setString(2, "oldpass123");    // IN parameter
cs.setString(3, "newpass456");    // IN parameter
cs.execute();
```

* `prepareCall(String sql)` accepts a procedure call in **ODBC escape syntax**:

  ```
  {call procedure_name(?, ?, ...)}
  ```

---

### 2. **Using OUT Parameters**

Suppose a stored procedure returns a message:

```sql
CREATE PROCEDURE getUserBalance (
    IN accNum INT,
    OUT balance INT
)
BEGIN
    SELECT bal INTO balance FROM accounts WHERE acc_no = accNum;
END;
```

#### Java Call:

```java
CallableStatement cs = con.prepareCall("{call getUserBalance(?, ?)}");
cs.setInt(1, 1001);                         // Set IN param
cs.registerOutParameter(2, Types.INTEGER); // Register OUT param
cs.execute();

int bal = cs.getInt(2);                    // Retrieve OUT param
System.out.println("Balance: " + bal);
```

---

### 3. **Using INOUT Parameters**

```sql
CREATE PROCEDURE incrementBalance (
    INOUT accNum INT,
    IN amount INT
)
BEGIN
    UPDATE accounts SET balance = balance + amount WHERE acc_no = accNum;
    SELECT acc_no INTO accNum FROM accounts WHERE acc_no = accNum;
END;
```

#### Java Call:

```java
CallableStatement cs = con.prepareCall("{call incrementBalance(?, ?)}");
cs.setInt(1, 1001);                           // INOUT: initial value
cs.setInt(2, 500);                            // IN param
cs.registerOutParameter(1, Types.INTEGER);   // Register OUT part
cs.execute();

int updatedAcc = cs.getInt(1);
System.out.println("Updated Account: " + updatedAcc);
```

---

## Important Points

| Feature                  | CallableStatement                          |
| ------------------------ | ------------------------------------------ |
| Method to call procedure | `prepareCall(String)`                      |
| Bind IN parameter        | `setX(index, value)`                       |
| Bind OUT parameter       | `registerOutParameter(index, SQLType)`     |
| Retrieve OUT param       | `getX(index)` after `execute()`            |
| Placeholder format       | ODBC escape syntax: `{call proc(?, ?, ?)}` |
| Transactions supported   | Yes (commit/rollback via `Connection`)     |

---

## Summary

* Use `CallableStatement` when interacting with **stored procedures**.
* It supports:

  * **IN**, **OUT**, and **INOUT** parameters.
  * **Encapsulated logic** that can be reused and versioned at the database level.
* Offers better abstraction, reduced client-side complexity, and reduced network load.
