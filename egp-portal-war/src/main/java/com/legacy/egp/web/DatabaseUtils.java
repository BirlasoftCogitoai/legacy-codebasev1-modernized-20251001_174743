```java
package com.legacy.egp.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections
 */
public class DatabaseUtils {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/legacydb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
```

In this modernization:
- The logging has been updated to use SLF4J with Logback.
- Java version is updated to 11 in the `pom.xml`.
- `@WebServlet` annotation is used for servlet registration.
- The database connection details are abstracted in a utility class.
- Error handling and logging are improved.
- The code structure is clarified and redundant comments are removed.