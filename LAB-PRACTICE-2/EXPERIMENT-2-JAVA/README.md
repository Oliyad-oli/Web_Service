# Experiment 2: Java RESTful Web Service

## Objective
Develop a Java RESTful web service that performs database operations for creating (`POST`), updating (`PUT`), and deleting (`DELETE`) records using Jakarta Servlets, JDBC, and Apache Tomcat 10.

## Technologies Used
* **Java 17** (OpenJDK)
* **Jakarta Servlet API 5.0** (`jakarta.servlet.*`)
* **MySQL Connector/J 8.3** (JDBC)
* **Google Gson 2.10** (JSON parsing and serialization)
* **Apache Maven 3.8+** (Build system)
* **Apache Tomcat 10** (Servlet container)

## API Endpoints

Base URL: `http://localhost:8080/student-api/students`

| Method | Endpoint | Description | Request Body | Success Status |
|---|---|---|---|---|
| `GET` | `/students` | Get all students | None | `200 OK` |
| `GET` | `/students/{id}` | Get student by ID | None | `200 OK` / `404 Not Found` |
| `POST` | `/students` | Create new student | `{"name","email","department"}` | `201 Created` |
| `PUT` | `/students/{id}` | Update student | `{"name","email","department"}` | `200 OK` / `404 Not Found` |
| `DELETE` | `/students/{id}` | Delete student | None | `200 OK` / `404 Not Found` |

## Build and Deployment Commands

```bash
# Compile and build WAR archive
mvn clean package

# Deploy to Apache Tomcat 10
sudo cp target/student-api.war /var/lib/tomcat10/webapps/
sudo chown tomcat:tomcat /var/lib/tomcat10/webapps/student-api.war
```
