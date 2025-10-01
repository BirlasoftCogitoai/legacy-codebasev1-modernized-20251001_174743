```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EGP Portal - Audit Log</title>
    <link rel="stylesheet" type="text/css" href="../styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Government Portal - Audit Log</h1>
        <nav>
            <ul>
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="audit.jsp">Audit Log</a></li>
                <li><a href="users.jsp">User Administration</a></li>
            </ul>
        </nav>
    </div>
    <div class="content">
        <h2>Audit Logs</h2>
        <c:if test="${not empty auditLogs}">
            <table>
                <thead>
                    <tr>
                        <th>Timestamp</th>
                        <th>User</th>
                        <th>Action</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="log" items="${auditLogs}">
                        <tr>
                            <td>${log.timestamp}</td>
                            <td>${log.user}</td>
                            <td>${log.action}</td>
                            <td>${log.details}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty auditLogs}">
            <p>No audit logs available.</p>
        </c:if>
    </div>
</body>
</html>
```