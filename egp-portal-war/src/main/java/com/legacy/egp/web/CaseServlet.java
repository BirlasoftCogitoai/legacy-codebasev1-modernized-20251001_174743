```java
package com.legacy.egp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Modernized Case Management Servlet
 */
@WebServlet(name = "CaseServlet", urlPatterns = {"/case"})
public class CaseServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(CaseServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Add your business logic here
            response.getWriter().write("Case Management GET response");
        } catch (Exception e) {
            logger.error("Error processing GET request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Add your business logic here
            response.getWriter().write("Case Management POST response");
        } catch (Exception e) {
            logger.error("Error processing POST request", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred");
        }
    }
}
```