<?php
/**
 * ============================================================================
 * WEB SERVICES LAB PRACTICE 2 - EXPERIMENT 1
 * Program Title: Vendor Parts RESTful Web Service (parts.php)
 * Description: Retrieves parts for a given vendor where part_id >= 1000
 *              and outputs results in valid XML format.
 * ============================================================================
 */

// Set response header to XML with UTF-8 encoding
header("Content-Type: application/xml; charset=UTF-8");

// Set CORS headers for REST API interoperability
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Headers: Content-Type");

// Include the PDO database connection
require_once __DIR__ . '/db.php';

/**
 * Generates an XML formatted error response with an HTTP status code.
 *
 * @param int $statusCode HTTP response status code
 * @param string $message Descriptive error message
 * @return void
 */
function sendXmlError(int $statusCode, string $message): void {
    http_response_code($statusCode);

    $dom = new DOMDocument('1.0', 'UTF-8');
    $dom->formatOutput = true;

    $root = $dom->createElement('response');
    $dom->appendChild($root);

    $statusNode = $dom->createElement('status', 'error');
    $codeNode = $dom->createElement('code', (string)$statusCode);
    $msgNode = $dom->createElement('message');
    $msgNode->appendChild($dom->createTextNode($message));

    $root->appendChild($statusNode);
    $root->appendChild($codeNode);
    $root->appendChild($msgNode);

    echo $dom->saveXML();
    exit;
}

// -----------------------------------------------------------------------------
// 1. Validate HTTP Request Method
// -----------------------------------------------------------------------------
if ($_SERVER['REQUEST_METHOD'] !== 'GET') {
    sendXmlError(405, "Method Not Allowed. Only GET requests are accepted.");
}

// -----------------------------------------------------------------------------
// 2. Validate 'vendor_id' Parameter
// -----------------------------------------------------------------------------
if (!isset($_GET['vendor_id']) || trim($_GET['vendor_id']) === '') {
    sendXmlError(400, "Bad Request: Missing required parameter 'vendor_id'. Example: parts.php?vendor_id=1");
}

$vendorIdRaw = trim($_GET['vendor_id']);
$vendorId = filter_var($vendorIdRaw, FILTER_VALIDATE_INT, ["options" => ["min_range" => 1]]);

if ($vendorId === false) {
    sendXmlError(400, "Bad Request: 'vendor_id' must be a positive integer. Received: '{$vendorIdRaw}'");
}

// -----------------------------------------------------------------------------
// 3. Query Database Using Prepared Statements
// -----------------------------------------------------------------------------
try {
    $pdo = getDbConnection();

    // First: Check if the vendor actually exists
    $vendorStmt = $pdo->prepare("SELECT vendor_id, vendor_name FROM vendors WHERE vendor_id = :vendor_id");
    $vendorStmt->execute([':vendor_id' => $vendorId]);
    $vendor = $vendorStmt->fetch();

    if (!$vendor) {
        sendXmlError(404, "Not Found: No vendor exists with vendor_id = {$vendorId}.");
    }

    // Second: Retrieve all parts belonging to the vendor where part_id >= 1000
    // Requirement 9: Only return records where the part ID is greater than or equal to 1000.
    $sql = "SELECT p.part_id, p.part_name, p.vendor_id, v.vendor_name
            FROM parts p
            INNER JOIN vendors v ON p.vendor_id = v.vendor_id
            WHERE p.vendor_id = :vendor_id AND p.part_id >= 1000
            ORDER BY p.part_id ASC";

    $partsStmt = $pdo->prepare($sql);
    $partsStmt->execute([':vendor_id' => $vendorId]);
    $parts = $partsStmt->fetchAll();

    // -------------------------------------------------------------------------
    // 4. Handle Empty Results (Vendor exists, but has no parts with ID >= 1000)
    // -------------------------------------------------------------------------
    if (empty($parts)) {
        http_response_code(404);
        $dom = new DOMDocument('1.0', 'UTF-8');
        $dom->formatOutput = true;
        
        $root = $dom->createElement('parts');
        $dom->appendChild($root);
        
        $notice = $dom->createElement('message');
        $notice->appendChild($dom->createTextNode(
            "No parts found with part_id >= 1000 for vendor '{$vendor['vendor_name']}' (ID: {$vendorId})."
        ));
        $root->appendChild($notice);

        echo $dom->saveXML();
        exit;
    }

    // -------------------------------------------------------------------------
    // 5. Construct Required XML Response
    // -------------------------------------------------------------------------
    http_response_code(200);

    $dom = new DOMDocument('1.0', 'UTF-8');
    $dom->formatOutput = true;

    $root = $dom->createElement('parts');
    $dom->appendChild($root);

    foreach ($parts as $row) {
        $partElement = $dom->createElement('part');

        // <part_id>
        $partIdElem = $dom->createElement('part_id', (string)$row['part_id']);
        $partElement->appendChild($partIdElem);

        // <part_name>
        $partNameElem = $dom->createElement('part_name');
        $partNameElem->appendChild($dom->createTextNode($row['part_name']));
        $partElement->appendChild($partNameElem);

        // <vendor_id>
        $vendorIdElem = $dom->createElement('vendor_id', (string)$row['vendor_id']);
        $partElement->appendChild($vendorIdElem);

        // <vendor_name>
        $vendorNameElem = $dom->createElement('vendor_name');
        $vendorNameElem->appendChild($dom->createTextNode($row['vendor_name']));
        $partElement->appendChild($vendorNameElem);

        $root->appendChild($partElement);
    }

    echo $dom->saveXML();

} catch (PDOException $e) {
    error_log("Database error in parts.php: " . $e->getMessage());
    sendXmlError(500, "Internal Server Error: A database error occurred while processing the request.");
}
