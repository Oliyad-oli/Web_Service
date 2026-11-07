<?php
/**
 * ============================================================================
 * WEB SERVICES LAB PRACTICE 2 - EXPERIMENT 1
 * Group Members: [Your Name and Group Members]
 * Program Title: Database Connection Module (db.php)
 * Description: Establishes a secure PDO connection to MySQL database 'parts_db'.
 * ============================================================================
 */

// Database Configuration Settings
define('DB_HOST', '127.0.0.1');      // Use 127.0.0.1 to avoid Linux socket issues
define('DB_PORT', '3306');           // Default MySQL port
define('DB_NAME', 'parts_db');        // Experiment 1 database name
define('DB_USER', 'labuser');         // MySQL username (change if using root)
define('DB_PASS', 'LabPassword123!'); // MySQL password
define('DB_CHARSET', 'utf8mb4');      // UTF-8 encoding

/**
 * Returns a configured PDO database connection instance.
 *
 * @return PDO The active PDO connection
 * @throws PDOException If the connection fails
 */
function getDbConnection(): PDO {
    $dsn = "mysql:host=" . DB_HOST . ";port=" . DB_PORT . ";dbname=" . DB_NAME . ";charset=" . DB_CHARSET;
    
    $options = [
        PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,  // Throw exceptions on SQL errors
        PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,        // Return rows as associative arrays
        PDO::ATTR_EMULATE_PREPARES   => false,                   // Use native prepared statements
        PDO::ATTR_TIMEOUT            => 5,                       // 5-second timeout
    ];

    try {
        return new PDO($dsn, DB_USER, DB_PASS, $options);
    } catch (PDOException $e) {
        // Log the error message internally for security
        error_log("Database connection error: " . $e->getMessage());
        throw $e;
    }
}

// ============================================================================
// Self-Test Execution Block
// Only run if db.php is executed directly (not included by parts.php)
// ============================================================================
if (isset($_SERVER['SCRIPT_FILENAME']) && realpath(__FILE__) === realpath($_SERVER['SCRIPT_FILENAME'])) {
    try {
        $testConn = getDbConnection();
        $stmt = $testConn->query("SELECT DATABASE() AS current_db, VERSION() AS mysql_ver");
        $info = $stmt->fetch();
        
        $msg = "SUCCESS: Successfully connected to MySQL database '{$info['current_db']}' (Version: {$info['mysql_ver']}).\n";
        echo $msg;
    } catch (PDOException $e) {
        $errMsg = "FAILURE: Could not connect to database. Error: " . $e->getMessage() . "\n";
        echo $errMsg;
    }
}
