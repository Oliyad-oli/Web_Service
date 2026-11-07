# Experiment 1: PHP RESTful Web Service

## Objective
Develop a PHP RESTful web service that retrieves all parts belonging to a particular vendor from a MySQL database and returns the results in XML format, returning only records where `part_id >= 1000`.

## Architecture & Files
* **`sql/setup_parts_db.sql`**: Schema definition and realistic sample records for `vendors` and `parts` tables.
* **`sql/create_user.sql`**: Dedicated MySQL user setup (`labuser`).
* **`db.php`**: Reusable PDO database connection module with error handling.
* **`parts.php`**: RESTful API endpoint accepting `vendor_id` and generating XML response.

## API Specification

### Endpoint: `GET /experiment1/parts.php?vendor_id={id}`
* **Query Parameters:**
  * `vendor_id` (required, integer > 0): The unique ID of the vendor.
* **Response Content-Type:** `application/xml; charset=UTF-8`

### Example Request
```http
GET http://localhost/experiment1/parts.php?vendor_id=1 HTTP/1.1
```

### Example Response (`200 OK`)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<parts>
  <part>
    <part_id>1001</part_id>
    <part_name>Mechanical Keyboard</part_name>
    <vendor_id>1</vendor_id>
    <vendor_name>ABC Company</vendor_name>
  </part>
  <part>
    <part_id>1002</part_id>
    <part_name>Wireless Optical Mouse</part_name>
    <vendor_id>1</vendor_id>
    <vendor_name>ABC Company</vendor_name>
  </part>
</parts>
```

### Status Codes Handled
* `200 OK`: Successful query with matching parts.
* `400 Bad Request`: Missing or non-integer `vendor_id`.
* `404 Not Found`: Vendor does not exist or vendor has no parts with `part_id >= 1000`.
* `405 Method Not Allowed`: Non-GET HTTP methods.
* `500 Internal Server Error`: Database connection or execution failure.
