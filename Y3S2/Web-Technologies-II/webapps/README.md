# Web Tech II: Tomcat Java WebApp Build & Database Setup

This project is part of the **Web Technologies II Labs** course. It involves Java-based web applicationa deployed under Apache Tomcat, with JDBC MySQL integration and servlet-based database setup. The following instructions detail how to compile the application, configure the MySQL database connection, and set up the required database tables and data.

> Note: Note that certain questions in this assignment may require manual compilation or additional checks.
---

## 1. Building and Compiling Java Files

All Java source files are located under various sub-directories in `ROOT` directory. To compile them into the appropriate location (`ROOT/WEB-INF/classes`), we have **three options**:

### Option 1: Bash Script

From the webapps, run:

```bash
./compile.sh
```

### Option 2: Batch Script (Windows)

Run the following command from the command line in the project webapps:

```cmd
compile.bat
```

### Option 3: Makefile

Alternatively, navigate to the `ROOT/WEB-INF` directory and run:

```bash
make
```

All `.java` files will be compiled and placed under:

```
webapps/ROOT/WEB-INF/classes/
```

---

## 2. JDBC MySQL Connection Configuration

Certain questions in this assignment require integration with a MySQL database. To configure the JDBC MySQL connection, you need to modify the MySQL `Resource` configuration in `context.xml`. This file is located at `ROOT/META-INF/context.xml`.

### Example MySQL Resource Configuration

```xml
<Context>
  <Resource
      name="jdbc/mydb"
      auth="Container"
      type="javax.sql.DataSource"
      username="your_mysql_user"
      password="your_mysql_password"
      driverClassName="com.mysql.cj.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/your_db_name"
      maxTotal="20"
      maxIdle="10"
      maxWaitMillis="-1"/>
</Context>
```

This resource will be used throughout the application for consistent database connectivity.

---

## 3. Database Setup via Servlets

Inside the `ROOT/Database/` directory, there are servlets that help manage the database schema and content. These servlets allow you to:

### Available Functionality

- Create necessary database tables
- Insert data
- Drop or truncate tables

### Important Restriction

These servlets are designed to work only with tables whose names start with `1100`. This prefix is hardcoded in the servlet logic, so only the intended tables will be affected.

---

## 4. Project Structure

The project follows the standard web application structure and includes the following directories and files:

```
webapps/
├── compile.sh            # Bash compile script
├── compile.bat           # Batch compile script
└── ROOT/
    ├── WEB-INF/
    │   ├── classes/      # Compiled .class files are placed here
    │   ├── lib/          # External libraries (e.g., JDBC driver)
    |   ├── views/        # Tomcat directory listings
    |   ├── Makefile      # Tomcat directory listings
    │   └── web.xml       # Deployment descriptor
    ├── META-INF/
    │   └── context.xml   # MySQL resource configuration
    ├── Database/         # Database-related servlets (Q01 - Q32)
    ├── assets/           # Assets for the web application
    └──  Q01 - Q32/       # Assignment solutions (Q01 - Q32)
```

---

## 5. Requirements

Before running the application, make sure the following are installed and configured:

- **Apache Tomcat 9.x**
- **Java JDK 8 or newer**
- **MySQL Server**

---