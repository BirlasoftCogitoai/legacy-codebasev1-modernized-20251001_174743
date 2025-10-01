```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EGP Portal - User Administration</title>
    <link rel="stylesheet" type="text/css" href="../styles.css">
</head>
<body>
    <div class="header">
        <h1>Enterprise Government Portal - User Administration</h1>
        <nav>
            <ul>
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="audit.jsp">Audit Log</a></li>
                <li><a href="users.jsp">User Administration</a></li>
            </ul>
        </nav>
    </div>
    <div class="content">
        <h2>Manage Users</h2>
        <c:if test="${not empty users}">
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.role}</td>
                            <td>${user.status}</td>
                            <td>
                                <a href="editUser.jsp?id=${user.id}">Edit</a>
                                <a href="deleteUser.jsp?id=${user.id}" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty users}">
            <p>No users available.</p>
        </c:if>
    </div>
</body>
</html>
```

# jenkins/Jenkinsfile
```groovy
// Modern Jenkins Pipeline for EGP Application
// Updated for modern CI/CD practices

pipeline {
    agent any

    tools {
        maven 'Maven-3.8.1'
        jdk 'JDK-11'
    }

    environment {
        MAVEN_OPTS = '-Xmx2048m'
        JAVA_OPTS = '-Xms512m -Xmx2048m'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/BirlasoftCogitoai/legacy-codebasev1.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def server = input message: 'Deploy to which server?', parameters: [choice(name: 'Server', choices: ['JBoss', 'WebLogic'], description: 'Select deployment server')]
                    if (server == 'JBoss') {
                        sh 'scp target/egp-portal.war user@jboss-server:/path/to/deploy'
                    } else if (server == 'WebLogic') {
                        sh 'scp target/egp-portal.war user@weblogic-server:/path/to/deploy'
                    }
                }
            }
        }
    }

    post {
        success {
            mail to: 'team@example.com', subject: "Build Successful: ${env.BUILD_TAG}", body: "The build ${env.BUILD_TAG} was successful."
        }
        failure {
            mail to: 'team@example.com', subject: "Build Failed: ${env.BUILD_TAG}", body: "The build ${env.BUILD_TAG} failed. Please check the logs for details."
        }
    }
}
```