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

/**
 * Profile Management Servlet
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {
    
    private static final Logger logger = LoggerFactory.getLogger(ProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            logger.warn("Session not found, redirecting to login.");
            response.sendRedirect("login.jsp");
            return;
        }

        // Example logic - this should be replaced with actual logic
        String userProfile = (String) session.getAttribute("userProfile");
        if (userProfile == null) {
            logger.error("User profile not found in session.");
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "User profile not found.");
            return;
        }

        request.setAttribute("profile", userProfile);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("ProfileServlet initialized.");
    }

    @Override
    public void destroy() {
        super.destroy();
        logger.info("ProfileServlet destroyed.");
    }
}
```