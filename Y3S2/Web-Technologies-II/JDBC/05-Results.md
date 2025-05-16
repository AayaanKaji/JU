## `ResultSet` Interface

Represents the **tabular data returned** by SQL queries (e.g., `SELECT`). It provides a **cursor** that moves row by row through the data.

### Key Properties:

- Initially, cursor is **before the first row**.
- Use `.next()` to move to the next row.
- Use `.getXxx(column)` to extract data by name or index:

  ```java
  rs.getString("username");
  rs.getInt(1);
  ```

---

## Cursor Navigation Methods

| Method               | Description                                                   |
| -------------------- | ------------------------------------------------------------- |
| `next()`             | Moves cursor to next row; returns false if no more.           |
| `previous()`         | Moves to previous row (requires scrollable `ResultSet`).      |
| `absolute(int row)`  | Moves to an absolute row index (1-based, -1 = last).          |
| `relative(int rows)` | Moves relative to current position (e.g., `-1` for previous). |
| `first()`            | Moves to the first row.                                       |
| `last()`             | Moves to the last row.                                        |
| `beforeFirst()`      | Places cursor before the first row.                           |
| `afterLast()`        | Places cursor after the last row.                             |

---

## Creating Scrollable/Updatable `ResultSet`

```java
Statement stmt = conn.createStatement(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY
);

ResultSet rs = stmt.executeQuery("SELECT * FROM table");
```

### Scroll Types (`resultSetType`):

- `TYPE_FORWARD_ONLY`: Default, only forward movement.
- `TYPE_SCROLL_INSENSITIVE`: Allows navigation; does **not** reflect DB changes made by others.
- `TYPE_SCROLL_SENSITIVE`: Reflects concurrent changes from other users.

### Concurrency (`resultSetConcurrency`):

- `CONCUR_READ_ONLY`: Cannot change DB via `ResultSet`.
- `CONCUR_UPDATABLE`: Allows direct update/delete/insert from `ResultSet`.

---

## Scrollability Behavior Table

| Scroll Type          | Internal/External INSERT | Internal UPDATE | Internal DELETE | External UPDATE | External DELETE |
| -------------------- | ------------------------ | --------------- | --------------- | --------------- | --------------- |
| `FORWARD_ONLY`       | No                       | Yes             | No              | No              | No              |
| `SCROLL_INSENSITIVE` | No                       | Yes             | Yes             | No              | No              |
| `SCROLL_SENSITIVE`   | No                       | Yes             | Yes             | Yes             | No              |

---

## `ResultSetMetaData`

Used to **inspect the structure** of a `ResultSet` at runtime (e.g., number of columns, names, types):

```java
ResultSet rs = stmt.executeQuery("SELECT * FROM questions");
ResultSetMetaData rsmd = rs.getMetaData();

int colCount = rsmd.getColumnCount();
for (int i = 1; i <= colCount; i++) {
    System.out.println(rsmd.getColumnName(i));
    System.out.println(rsmd.getColumnTypeName(i));
}
```

---

## `DatabaseMetaData`

Provides **information about the DBMS** itself, such as supported SQL features, driver info, supported data types.

```java
DatabaseMetaData meta = conn.getMetaData();
System.out.println(meta.getDatabaseProductName());
System.out.println(meta.supportsBatchUpdates());
System.out.println(meta.getDriverVersion());
```

---

## Summary

| Feature              | Interface           | Purpose                                      |
| -------------------- | ------------------- | -------------------------------------------- |
| Query results        | `ResultSet`         | Cursor-based access to SQL query results     |
| Cursor movement      | `ResultSet`         | Navigate through rows forward/backward       |
| Structure of results | `ResultSetMetaData` | Inspect column names/types/count             |
| DB capabilities      | `DatabaseMetaData`  | Introspect supported SQL types/features      |
| Scrollability        | `ResultSet`         | Use appropriate `TYPE_...` constant          |
| Updatability         | `ResultSet`         | Use `CONCUR_UPDATABLE` if mutation is needed |

---
