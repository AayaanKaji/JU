# Web Tech II: Tomcat Java WebApp Build & Database Setup

This project is part of the **Web Technologies II Labs** course. It involves a Java-based web application deployed under Apache Tomcat, with JDBC MySQL integration and servlet-based database management.

> ⚠️ Certain questions in this assignment may require manual compilation.

---

## 1. Project Placement

Place the entire project directory inside the Tomcat `webapps/` directory:

```
TOMCAT_HOME/
└── webapps/
    └── AayaanKaji/      ← place this folder here
```

---

## 2. Compiling Java Source Files

All `.java` files for this project are located under various sub-directories. To compile them into the appropriate output directory (`/WEB-INF/classes/`), use one of the following methods:

### Option 1: Bash Script

From inside `webapps/`, run, after giving execution permission:

```bash
chmod +x compile.sh && ./compile.sh
```

### Option 2: Batch Script

From inside `webapps/`, run:

```cmd
compile.bat
```

### Option 3: Makefile

- Before running the make file, the source directory needs to updated to the absolute path of /AayaanKaji directory. 
From the `WEB-INF/` directory, run:

```bash
make
```

After compilation, all `.class` files will be located in:

```
/AayaanKaji/WEB-INF/classes/
```

---

## 3. JDBC MySQL Connection Configuration

The database connection is configured via a JDBC `Resource` in `AayaanKaji/META-INF/context.xml`. This defines the connection parameters used across the application.

### Sample Configuration

```xml
<Context>
  <Resource
      name="jdbc/mydb"
      auth="Container"
      type="javax.sql.DataSource"
      username="your_mysql_user"
      password="your_mysql_password"
      driverClassName="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://host:3306/db_name"
      maxTotal="20"
      maxIdle="10"
      maxWaitMillis="-1"/>
</Context>
```

Ensure this configuration matches your local MySQL setup.

---

## 4. Using Database Servlets

The `AayaanKaji/Database/` directory contains servlet classes to help manage the database schema and contents.

### Provided Functionality:

- **Create** necessary tables  
- **Insert** initial or sample data  
- **Drop** or **truncate** tables  
- **List** all tables starting with `1100` prefix

> ⚠️ Only tables with names starting with `1100` are affected — this constraint is hardcoded for safety.

To use these servlets, deploy the application and access them through a browser (e.g., `/Database/CreateTablesServlet`, `/Database/DropTablesServlet`, etc.).

---

## 5. Project Structure Overview

```
webapps/
|
└── AayaanKaji/
    ├── WEB-INF/
    │   ├── classes/         # Compiled .class files go here
    │   ├── lib/             # JDBC and other required libraries (already included)
    │   ├── views/           # Optional UI views
    |   ├──compile.sh        # Bash compile script
    |   ├──compile.bat       # Batch compile script
    │   ├── Makefile         # Makefile-based build support
    │   └── web.xml          # Deployment descriptor
    ├── META-INF/
    │   └── context.xml      # MySQL connection configuration
    ├── Database/            # Database-related servlets (Q01 - Q32)
    ├── assets/              # Static assets
    └── Q01 - Q32/           # Question-wise servlet solutions
```

---

## 6. Requirements

Make sure the following components are installed and correctly configured:

- **Apache Tomcat 9.x**
- **Java JDK 8 or newer**
- **MySQL Server** (with user and schema set up to match `context.xml`)

---