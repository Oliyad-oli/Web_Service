package com.lab;

import java.io.Serializable;

/**
 * ============================================================================
 * WEB SERVICES LAB PRACTICE 2 - EXPERIMENT 2
 * Program Title: Student Entity / POJO (Student.java)
 * Description: Represents a student record in the database.
 * ============================================================================
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private String department;
    private String createdAt;
    private String updatedAt;

    // Default no-argument constructor (required for serialization/JSON libraries)
    public Student() {
    }

    // Constructor with fields for creating a new student
    public Student(String name, String email, String department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }

    // Full constructor including ID and timestamps
    public Student(Integer id, String name, String email, String department, String createdAt, String updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Validates that required fields are present and well-formed.
     * @return null if valid; error message string if invalid.
     */
    public String validate() {
        if (name == null || name.trim().isEmpty()) {
            return "Field 'name' is required and cannot be empty.";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Field 'email' is required and cannot be empty.";
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            return "Field 'email' is invalid: '" + email + "' is not a valid email address.";
        }
        if (department == null || department.trim().isEmpty()) {
            return "Field 'department' is required and cannot be empty.";
        }
        return null;
    }
}
