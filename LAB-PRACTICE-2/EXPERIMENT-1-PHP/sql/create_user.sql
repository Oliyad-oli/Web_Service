-- Create dedicated user for Web Services Lab
CREATE USER IF NOT EXISTS 'labuser'@'localhost' IDENTIFIED BY 'LabPassword123!';
CREATE USER IF NOT EXISTS 'labuser'@'127.0.0.1' IDENTIFIED BY 'LabPassword123!';

-- Grant privileges for both experiments
GRANT ALL PRIVILEGES ON parts_db.* TO 'labuser'@'localhost';
GRANT ALL PRIVILEGES ON parts_db.* TO 'labuser'@'127.0.0.1';

GRANT ALL PRIVILEGES ON student_db.* TO 'labuser'@'localhost';
GRANT ALL PRIVILEGES ON student_db.* TO 'labuser'@'127.0.0.1';

FLUSH PRIVILEGES;
