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
curl -s -i -X POST "http://localhost:8080/student-api/students" \
  -H "Content-Type: application/json" \
  -d '{"name":"Kenean Belay","email":"kenean.belay@university.edu.et","department":"Artificial Intelligence"}'

echo -e "\n\n3. Test PUT - Update Student (Requirement 6.2):"
curl -s -i -X PUT "http://localhost:8080/student-api/students/4" \
  -H "Content-Type: application/json" \
  -d '{"name":"Kenean Belay Updated","email":"kenean.updated@university.edu.et","department":"Data Science"}'

echo -e "\n\n4. Test DELETE - Delete Student (Requirement 6.3):"
curl -s -i -X DELETE "http://localhost:8080/student-api/students/4"

echo -e "\n\n5. Test DELETE Non-Existent ID (Expected 404 Not Found):"
curl -s -i -X DELETE "http://localhost:8080/student-api/students/999"

echo -e "\n\n6. Test POST Invalid Payload (Expected 400 Bad Request):"
curl -s -i -X POST "http://localhost:8080/student-api/students" \
  -H "Content-Type: application/json" \
  -d '{"name":"","email":"invalid-email","department":"CS"}'

echo -e "\n\n${GREEN}All tests executed successfully.${NC}"
