```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EGP Portal - Cases</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Government Portal - Cases</h1>
    </div>
    <div class="content">
        <c:if test="${not empty caseList}">
            <ul>
                <c:forEach var="case" items="${caseList}">
                    <li>
                        <a href="caseDetails.jsp?id=${case.id}">${case.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty caseList}">
            <p>No cases available.</p>
        </c:if>
    </div>
    <div class="footer">
        <p>&copy; 2023 EGP Portal</p>
    </div>
</body>
</html>
```