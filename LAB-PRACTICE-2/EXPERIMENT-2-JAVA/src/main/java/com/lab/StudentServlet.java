package com.lab;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * WEB SERVICES LAB PRACTICE 2 - EXPERIMENT 2
 * Program Title: Student RESTful Web Service Servlet (StudentServlet.java)
 * Description: Implements REST API operations (POST, PUT, DELETE, GET)
 *              for the students table using Jakarta Servlet and JDBC.
 * ============================================================================
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/students", "/students/*"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Google Gson instance for JSON conversion
    private final Gson gson = new Gson();

    /**
     * Helper: Sets standard HTTP headers for JSON REST API and CORS.
     */
    private void setHeaders(HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }

    /**
     * Helper: Reads request body from HttpServletRequest into a String.
     */
    private String readRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString().trim();
    }

    /**
     * Helper: Sends a standardized JSON response with an HTTP status code.
     */
    private void sendJsonResponse(HttpServletResponse response, int statusCode, String status, String message, Object data)
            throws IOException {
        response.setStatus(statusCode);
        JsonObject json = new JsonObject();
        json.addProperty("status", status);
        json.addProperty("code", statusCode);
        json.addProperty("message", message);
        if (data != null) {
            json.add("data", gson.toJsonTree(data));
        }

        try (PrintWriter out = response.getWriter()) {
            out.print(gson.toJson(json));
            out.flush();
        }
    }

    /**
     * Helper: Extracts student ID from path (/students/{id}) or query parameter (?id={id}).
     *
     * @return Positive Integer ID, or null if not provided or invalid.
     */
    private Integer extractStudentId(HttpServletRequest request) {
        // 1. Check path info: e.g., /students/5
        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String[] parts = pathInfo.split("/");
            if (parts.length >= 2 && !parts[1].trim().isEmpty()) {
                try {
                    int id = Integer.parseInt(parts[1].trim());
                    if (id > 0) return id;
                } catch (NumberFormatException ignored) {}
            }
        }

        // 2. Check query parameter: e.g., /students?id=5
        String paramId = request.getParameter("id");
        if (paramId != null && !paramId.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(paramId.trim());
                if (id > 0) return id;
            } catch (NumberFormatException ignored) {}
        }

        return null;
    }

    // =========================================================================
    // HTTP POST: Create a new student record
    // =========================================================================
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setHeaders(response);

        String requestBody = readRequestBody(request);
        if (requestBody.isEmpty()) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error",
                    "Request body is empty. Please provide student JSON (name, email, department).", null);
            return;
        }

        Student student;
        try {
            student = gson.fromJson(requestBody, Student.class);
        } catch (JsonSyntaxException e) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error",
                    "Malformed JSON body: " + e.getMessage(), null);
            return;
        }

        if (student == null) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error",
                    "Invalid student JSON payload.", null);
            return;
        }

        // Validate mandatory fields
        String validationError = student.validate();
        if (validationError != null) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error", validationError, null);
            return;
        }

        // Prepared Statement SQL Insertion
        String sql = "INSERT INTO students (name, email, department) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getName().trim());
            stmt.setString(2, student.getEmail().trim());
            stmt.setString(3, student.getDepartment().trim());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error",
                        "Failed to create student: No rows affected.", null);
                return;
            }

            // Retrieve auto-generated ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                }
            }

            sendJsonResponse(response, HttpServletResponse.SC_CREATED, "success",
                    "Student record created successfully.", student);

        } catch (SQLException e) {
            // Check for duplicate email error (MySQL error code 1062)
            if (e.getErrorCode() == 1062) {
                sendJsonResponse(response, HttpServletResponse.SC_CONFLICT, "error",
                        "Conflict: A student with email '" + student.getEmail() + "' already exists.", null);
            } else {
                e.printStackTrace();
                sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error",
                        "Database error during creation: " + e.getMessage(), null);
            }
        }
    }

    // =========================================================================
    // HTTP PUT: Update an existing student record
    // =========================================================================
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setHeaders(response);

        Integer studentId = extractStudentId(request);

        String requestBody = readRequestBody(request);
        if (requestBody.isEmpty()) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error",
                    "Request body is empty. Please provide updated student JSON.", null);
            return;
        }

        Student student;
        try {
            student = gson.fromJson(requestBody, Student.class);
        } catch (JsonSyntaxException e) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error",
                    "Malformed JSON body: " + e.getMessage(), null);
            return;
        }

        // If ID wasn't in URL path/query, check if present in JSON body
        if (studentId == null && student != null && student.getId() != null && student.getId() > 0) {
            studentId = student.getId();
        }

        if (studentId == null) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error",
                    "Missing or invalid student ID. Provide it in URL path (e.g. /students/1), query (?id=1), or JSON body.", null);
            return;
        }

        // Validate payload data
        String validationError = student.validate();
        if (validationError != null) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error", validationError, null);
            return;
        }

        student.setId(studentId);

        // Verify existence and perform update using PreparedStatement
        String checkSql = "SELECT id FROM students WHERE id = ?";
        String updateSql = "UPDATE students SET name = ?, email = ?, department = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            // 1. Check if record exists
            try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
                checkStmt.setInt(1, studentId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        sendJsonResponse(response, HttpServletResponse.SC_NOT_FOUND, "error",
                                "Not Found: No student exists with ID " + studentId + ".", null);
                        return;
                    }
                }
            }

            // 2. Perform update
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setString(1, student.getName().trim());
                updateStmt.setString(2, student.getEmail().trim());
                updateStmt.setString(3, student.getDepartment().trim());
                updateStmt.setInt(4, studentId);

                updateStmt.executeUpdate();

                sendJsonResponse(response, HttpServletResponse.SC_OK, "success",
                        "Student record updated successfully.", student);
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                sendJsonResponse(response, HttpServletResponse.SC_CONFLICT, "error",
                        "Conflict: The email '" + student.getEmail() + "' is already in use by another student.", null);
            } else {
                e.printStackTrace();
                sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error",
                        "Database error during update: " + e.getMessage(), null);
            }
        }
    }

    // =========================================================================
    // HTTP DELETE: Delete a student record by ID
    // =========================================================================
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setHeaders(response);

        Integer studentId = extractStudentId(request);

        if (studentId == null) {
            sendJsonResponse(response, HttpServletResponse.SC_BAD_REQUEST, "error",
                    "Missing or invalid student ID for deletion. Specify ID in URL path (e.g. /students/1) or query (?id=1).", null);
            return;
        }

        String deleteSql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteSql)) {

            stmt.setInt(1, studentId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                sendJsonResponse(response, HttpServletResponse.SC_NOT_FOUND, "error",
                        "Not Found: Cannot delete student. No record found with ID " + studentId + ".", null);
            } else {
                JsonObject deletedInfo = new JsonObject();
                deletedInfo.addProperty("deletedId", studentId);
                sendJsonResponse(response, HttpServletResponse.SC_OK, "success",
                        "Student record with ID " + studentId + " deleted successfully.", deletedInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error",
                    "Database error during deletion: " + e.getMessage(), null);
        }
    }

    // =========================================================================
    // HTTP GET: Retrieve all students or a single student by ID
    // =========================================================================
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setHeaders(response);

        Integer studentId = extractStudentId(request);

        try (Connection conn = DBConnection.getConnection()) {
            if (studentId != null) {
                // Fetch single student
                String sql = "SELECT id, name, email, department, created_at, updated_at FROM students WHERE id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, studentId);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            Student s = new Student(
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("email"),
                                    rs.getString("department"),
                                    rs.getString("created_at"),
                                    rs.getString("updated_at")
                            );
                            sendJsonResponse(response, HttpServletResponse.SC_OK, "success",
                                    "Student retrieved successfully.", s);
                        } else {
                            sendJsonResponse(response, HttpServletResponse.SC_NOT_FOUND, "error",
                                    "Not Found: No student exists with ID " + studentId + ".", null);
                        }
                    }
                }
            } else {
                // Fetch all students
                String sql = "SELECT id, name, email, department, created_at, updated_at FROM students ORDER BY id ASC";
                try (PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    List<Student> list = new ArrayList<>();
                    while (rs.next()) {
                        list.add(new Student(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("email"),
                                rs.getString("department"),
                                rs.getString("created_at"),
                                rs.getString("updated_at")
                        ));
                    }
                    sendJsonResponse(response, HttpServletResponse.SC_OK, "success",
                            "All students retrieved successfully.", list);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            sendJsonResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error",
                    "Database error during retrieval: " + e.getMessage(), null);
        }
    }

    // =========================================================================
    // HTTP OPTIONS: Pre-flight check for CORS
    // =========================================================================
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
