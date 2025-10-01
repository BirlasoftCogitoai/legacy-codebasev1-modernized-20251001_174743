```java
package com.legacy.egp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Modernized Login Servlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DatabaseUtils.getConnection()) {
            String hashedPassword = hashPassword(password);
            String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashedPassword);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", username);
                        response.sendRedirect("home");
                    } else {
                        response.sendRedirect("login?error=true");
                    }
                }
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            logger.error("Error during login process", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
```