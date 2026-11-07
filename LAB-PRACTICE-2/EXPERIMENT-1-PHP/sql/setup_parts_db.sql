-- =============================================================================
-- WEB SERVICES LAB PRACTICE 2 - EXPERIMENT 1
-- Database Setup Script: parts_db
-- =============================================================================

-- Step 1: Create the database
CREATE DATABASE IF NOT EXISTS parts_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE parts_db;

-- Step 2: Drop existing tables if they exist (clean slate)
DROP TABLE IF EXISTS parts;
DROP TABLE IF EXISTS vendors;

-- Step 3: Create the 'vendors' table
CREATE TABLE vendors (
    vendor_id INT AUTO_INCREMENT PRIMARY KEY,
    vendor_name VARCHAR(100) NOT NULL,
    contact_email VARCHAR(100),
    country VARCHAR(50) DEFAULT 'Ethiopia',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- Step 4: Create the 'parts' table with Foreign Key relationship
CREATE TABLE parts (
    part_id INT PRIMARY KEY,
    part_name VARCHAR(100) NOT NULL,
    vendor_id INT NOT NULL,
    unit_price DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    stock_quantity INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_parts_vendor
        FOREIGN KEY (vendor_id)
        REFERENCES vendors(vendor_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Step 5: Insert realistic sample data into 'vendors'
INSERT INTO vendors (vendor_id, vendor_name, contact_email, country) VALUES
(1, 'ABC Company', 'contact@abccompany.com', 'Ethiopia'),
(2, 'Global Tech Supplies', 'sales@globaltech.com', 'Germany'),
(3, 'Micro Electronic Components', 'support@microparts.com', 'Japan'),
(4, 'Apex Hardware Distributors', 'info@apexhardware.com', 'United States');

-- Step 6: Insert realistic sample data into 'parts'
-- IMPORTANT: Include parts with part_id >= 1000 AND part_id < 1000
-- to verify that the query filter (part_id >= 1000) works correctly.

-- Parts for Vendor 1 (ABC Company)
-- Expected to return 1001 and 1002, but exclude 501
INSERT INTO parts (part_id, part_name, vendor_id, unit_price, stock_quantity) VALUES
(1001, 'Mechanical Keyboard', 1, 45.00, 150),
(1002, 'Wireless Optical Mouse', 1, 22.50, 200),
(501,  'USB 2.0 Cable', 1, 3.50, 500);

-- Parts for Vendor 2 (Global Tech Supplies)
-- Expected to return 1003 and 1004, but exclude 999
INSERT INTO parts (part_id, part_name, vendor_id, unit_price, stock_quantity) VALUES
(1003, '27-inch 4K IPS Monitor', 2, 320.00, 45),
(1004, 'USB-C Multiport Hub', 2, 35.00, 120),
(999,  'VGA to HDMI Adapter', 2, 12.00, 80);

-- Parts for Vendor 3 (Micro Electronic Components)
-- Only has part_id < 1000 (test case for vendor with NO parts >= 1000)
INSERT INTO parts (part_id, part_name, vendor_id, unit_price, stock_quantity) VALUES
(301, '5V Relay Module', 3, 2.50, 300),
(450, 'Breadboard Jumper Wires', 3, 1.75, 450);

-- Vendor 4 (Apex Hardware Distributors)
-- Has NO parts at all (test case for empty vendor)

-- Step 7: Verification Query (matches the lab service logic)
SELECT p.part_id, p.part_name, p.vendor_id, v.vendor_name
FROM parts p
INNER JOIN vendors v ON p.vendor_id = v.vendor_id
WHERE p.vendor_id = 1 AND p.part_id >= 1000
ORDER BY p.part_id ASC;
