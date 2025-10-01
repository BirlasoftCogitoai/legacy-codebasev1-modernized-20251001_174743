```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>EGP Portal - Home</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Government Portal - Home</h1>
    </div>
    <div class="content">
        <c:if test="${not empty welcomeMessage}">
            <p>${welcomeMessage}</p>
        </c:if>
        <c:if test="${empty welcomeMessage}">
            <p>Welcome to the Enterprise Government Portal.</p>
        </c:if>
    </div>
    <div class="footer">
        <p>&copy; 2023 EGP Portal</p>
    </div>
</body>
</html>
```