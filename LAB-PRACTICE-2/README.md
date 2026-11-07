# Web Services Lab Practice 2: RESTful Web Services in PHP and Java

## Course Information
* **Course:** Web Services
* **Academic Year:** 2026-2027
* **Practice:** Lab Practice 2 - REST in PHP and Java

---

## Overview

This repository contains the complete implementation for **Web Services Lab Practice 2**, consisting of two independent experiments demonstrating RESTful web service architecture:

1. **Experiment 1 (PHP RESTful Service):**
   * Relational database `parts_db` in MySQL (`vendors` and `parts` tables).
   * REST API endpoint `parts.php` returning vendor parts where `part_id >= 1000`.
   * Formatted output in valid XML.
   * Full input validation and HTTP error status codes.

2. **Experiment 2 (Java RESTful Service):**
   * Maven Web Application built with Java 17 and Jakarta Servlet API.
   * Relational database `student_db` in MySQL (`students` table).
   * Full REST API supporting `POST` (Create), `PUT` (Update), and `DELETE` (Delete) operations.
   * Prepared statements for SQL injection prevention.
   * Deployed as a WAR archive on Apache Tomcat 10.

---

## Directory Structure

```text
LAB-PRACTICE-2/
│
├── EXPERIMENT-1-PHP/                 # PHP RESTful Service
│   ├── db.php                        # PDO Database Connection Module
│   ├── parts.php                     # REST Endpoint returning XML
│   ├── sql/                          # MySQL Setup and User Privilege Scripts
│   │   ├── create_user.sql
│   │   └── setup_parts_db.sql
│   └── README.md                     # Experiment 1 Documentation & API Guide
│
├── EXPERIMENT-2-JAVA/                # Java Maven Jakarta Servlet REST Service
│   ├── pom.xml                       # Maven Configuration (Java 17, Jakarta EE 5, MySQL, Gson)
│   ├── sql/                          # MySQL Setup for student_db
│   │   └── setup_student_db.sql
│   ├── src/main/java/com/lab/        # Java Source Code
│   │   ├── DBConnection.java         # JDBC Connection Provider
│   │   ├── Student.java              # Student POJO Entity with Validation
│   │   └── StudentServlet.java       # Jakarta REST Servlet (POST, PUT, DELETE, GET)
│   ├── src/main/webapp/WEB-INF/      # Deployment Descriptor
│   │   └── web.xml
│   └── README.md                     # Experiment 2 Documentation & Postman Guide
│
└── docs/                             # Lab Report & Documentation Assets
```
