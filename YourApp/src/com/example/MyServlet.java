/**
 * =========================================================================
 * HARAMAYA UNIVERSITY
 * COLLEGE OF COMPUTING AND INFORMATICS
 * DEPARTMENT OF SOFTWARE ENGINEERING
 * YEAR V – SEMESTER I | ACADEMIC YEAR: 2026-2027
 * COURSE: Web Services
 * HARAMAYA | OROMIA | ETHIOPIA
 * 
 * GROUP MEMBERS:
 * 1. Yabets Tinsae Dube   - ID: 1040/15
 * 2. Tesfalem Markos      - ID: 0993/15
 * 3. Abdi Tamene Tekuma   - ID: 0008/15
 * 4. Yosan Gonfa Merga    - ID: 1
 * 
 * TITLE OF THE PROGRAM:
 * Simple Servlet Implementation and Deployment for Tomcat 5.0
 * 
 * OUTPUT (COMPILATION & RUNTIME):
 * Compilation: ant deploy -> BUILD SUCCESSFUL (0 seconds)
 * Runtime: HTTP 200 OK -> "Hello from MyServlet!" at http://localhost:8080/YourApp/hello
 * =========================================================================
 */
package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello from MyServlet!");
    }
}
