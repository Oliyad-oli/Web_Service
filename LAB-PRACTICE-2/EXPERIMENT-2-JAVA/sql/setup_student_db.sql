-- =============================================================================
-- WEB SERVICES LAB PRACTICE 2 - EXPERIMENT 2
-- Database Setup Script: student_db
-- =============================================================================

-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS student_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE student_db;

-- Step 2: Drop table if it exists (clean slate)
DROP TABLE IF EXISTS students;

-- Step 3: Create 'students' table
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Step 4: Insert initial sample records for testing
INSERT INTO students (id, name, email, department) VALUES
(1, 'Abebe Bikila', 'abebe.bikila@university.edu.et', 'Computer Science'),
(2, 'Chaltu Tadesse', 'chaltu.tadesse@university.edu.et', 'Software Engineering'),
(3, 'Dawit Yohannes', 'dawit.yohannes@university.edu.et', 'Information Systems');

-- Reset AUTO_INCREMENT to start after sample records
ALTER TABLE students AUTO_INCREMENT = 4;

-- Step 5: Verification query
SELECT id, name, email, department, created_at FROM students;
