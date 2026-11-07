#!/usr/bin/env bash
# ==============================================================================
# WEB SERVICES LAB PRACTICE 2 - AUTOMATED TEST SUITE
# Tests all endpoints for Experiment 1 (PHP) and Experiment 2 (Java Tomcat)
# ==============================================================================

set -e
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m'

echo -e "${BLUE}==================================================================${NC}"
echo -e "${BLUE}       WEB SERVICES LAB PRACTICE 2 - AUTOMATED API TEST SUITE      ${NC}"
echo -e "${BLUE}==================================================================${NC}\n"

# ------------------------------------------------------------------------------
# EXPERIMENT 1: PHP RESTful Service
# ------------------------------------------------------------------------------
echo -e "${YELLOW}>>> [EXPERIMENT 1: PHP REST Service on Apache (:80)] <<<${NC}"

echo -e "\n1. Test GET vendor 1 (Multiple parts >= 1000):"
curl -s -i "http://localhost/experiment1/parts.php?vendor_id=1" | head -n 25

echo -e "\n2. Test GET vendor 2 (Multiple parts >= 1000):"
curl -s -i "http://localhost/experiment1/parts.php?vendor_id=2" | head -n 25

echo -e "\n3. Test GET vendor 3 (No parts >= 1000 -> Expected 404):"
curl -s -i "http://localhost/experiment1/parts.php?vendor_id=3" | head -n 15

echo -e "\n4. Test Missing vendor_id (Expected 400 Bad Request):"
curl -s -i "http://localhost/experiment1/parts.php" | head -n 15

echo -e "\n5. Test Invalid vendor_id string (Expected 400 Bad Request):"
curl -s -i "http://localhost/experiment1/parts.php?vendor_id=abc" | head -n 15

# ------------------------------------------------------------------------------
# EXPERIMENT 2: Java RESTful Service
# ------------------------------------------------------------------------------
echo -e "\n${YELLOW}>>> [EXPERIMENT 2: Java REST Service on Tomcat (:8080)] <<<${NC}"

echo -e "\n1. Test GET All Students:"
curl -s -i "http://localhost:8080/student-api/students" | head -n 20

echo -e "\n2. Test POST - Create Student (Requirement 6.1):"
TIMESTAMP=$(date +%s)
POST_RES=$(curl -s -w "\n%{http_code}" -X POST "http://localhost:8080/student-api/students" \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Kenean Belay\",\"email\":\"kenean.${TIMESTAMP}@university.edu.et\",\"department\":\"Artificial Intelligence\"}")
HTTP_CODE=$(echo "$POST_RES" | tail -n1)
POST_BODY=$(echo "$POST_RES" | head -n -1)
echo "HTTP Status: $HTTP_CODE"
echo "Response Body: $POST_BODY"

# Extract the newly created ID
NEW_ID=$(echo "$POST_BODY" | grep -o '"id":[0-9]*' | head -n1 | cut -d':' -f2)
echo "Created Student ID: $NEW_ID"

echo -e "\n3. Test PUT - Update Student (Requirement 6.2):"
PUT_RES=$(curl -s -w "\n%{http_code}" -X PUT "http://localhost:8080/student-api/students/${NEW_ID}" \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Kenean Belay Updated\",\"email\":\"kenean.up.${TIMESTAMP}@university.edu.et\",\"department\":\"Data Science\"}")
PUT_CODE=$(echo "$PUT_RES" | tail -n1)
PUT_BODY=$(echo "$PUT_RES" | head -n -1)
echo "HTTP Status: $PUT_CODE"
echo "Response Body: $PUT_BODY"

echo -e "\n4. Test DELETE - Delete Student (Requirement 6.3):"
DEL_RES=$(curl -s -w "\n%{http_code}" -X DELETE "http://localhost:8080/student-api/students/${NEW_ID}")
DEL_CODE=$(echo "$DEL_RES" | tail -n1)
DEL_BODY=$(echo "$DEL_RES" | head -n -1)
echo "HTTP Status: $DEL_CODE"
echo "Response Body: $DEL_BODY"

echo -e "\n\n5. Test DELETE Non-Existent ID (Expected 404 Not Found):"
curl -s -i -X DELETE "http://localhost:8080/student-api/students/999"

echo -e "\n\n6. Test POST Invalid Payload (Expected 400 Bad Request):"
curl -s -i -X POST "http://localhost:8080/student-api/students" \
  -H "Content-Type: application/json" \
  -d '{"name":"","email":"invalid-email","department":"CS"}'

echo -e "\n\n${GREEN}All tests executed successfully.${NC}"
