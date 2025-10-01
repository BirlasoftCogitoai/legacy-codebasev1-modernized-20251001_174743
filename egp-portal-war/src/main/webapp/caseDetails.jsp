```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EGP Portal - Case Details</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Government Portal - Case Details</h1>
    </div>
    <div class="content">
        <c:if test="${not empty caseDetails}">
            <h2>Case ID: ${caseDetails.id}</h2>
            <p>Status: ${caseDetails.status}</p>
            <p>Description: ${caseDetails.description}</p>
        </c:if>
        <c:if test="${empty caseDetails}">
            <p>No case details available.</p>
        </c:if>
    </div>
    <div class="footer">
        <p>&copy; 2023 EGP Portal</p>
    </div>
</body>
</html>
```