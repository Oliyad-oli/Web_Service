# Web Services Lab Practice 1 - Group Project Submission
**Haramaya University | College of Computing and Informatics | Department of Software Engineering**  
**Academic Year:** 2026–2027 | **Year V – Semester I**  
**Course:** Web Services  

---

## Group Members
1. **Yabets Tinsae Dube** — ID: `1040/15`
2. **Tesfalem Markos** — ID: `0993/15`
3. **Abdi Tamene Tekuma** — ID: `0008/15`
4. **Yosan Gonfa Merga** — ID: `1084/15`
5. **Oliyad Dandena** — ID: `1786/15`

---

## Submission Package Contents
* `LAB_PRACTICE_1_REPORT.md`: Comprehensive, step-by-step documentation covering **Experiments 1 through 16**.
* `YourApp/`: Complete Apache Ant project:
  * `build.xml`: Automated build script targeting Tomcat 5.0 JWSDP.
  * `src/com/example/MyServlet.java`: Java Servlet source with institutional header comments.
  * `web/WEB-INF/web.xml`: Deployment descriptor mapping `/hello`.
* `tomcat50-jwsdp/`:
  * `bin/setenv.sh`: Java 8 runtime isolation script.
  * `conf/server.xml`: Tomcat 5.0 server configuration.
  * `conf/tomcat-users.xml`: Security realm with `admin` and `manager` roles.
  * `webapps/YourApp`: Deployed servlet web application.

---

## How to Run & Verify
1. **Start Tomcat Server:**
   ```bash
   cd tomcat50-jwsdp
   ./bin/startup.sh
   ```
2. **Verify Homepage:**
   Open browser at `http://localhost:8080/`
3. **Deploy & Build Servlet:**
   ```bash
   cd ../YourApp
   ant deploy
   ```
4. **Access the Servlet:**
   Open browser at `http://localhost:8080/YourApp/hello`
   Expected response: `Hello from MyServlet!`
