# 🎓 Web Services — Lab Practice 1 Group Submission

**Haramaya University | College of Computing and Informatics | Department of Software Engineering**  
**Academic Year:** 2026–2027 | **Year V – Semester I** | **Course:** Web Services (SEng5071)  

---

## 🚀 Quick Access & Downloads

| Resource | Direct Link | Description |
|:---|:---|:---|
| 📥 **Official PDF Report** | [**Download LAB_PRACTICE_1_REPORT_FINAL.pdf**](LAB_PRACTICE_1_REPORT_FINAL.pdf) | Complete 31-page formatted PDF report with diagrams and terminal outputs |
| 🌐 **Live GitHub Pages** | [**oliyad-oli.github.io/Web_Service**](https://oliyad-oli.github.io/Web_Service/) | Interactive browsable web report hosted online |
| 📑 **Direct PDF on Web** | [**View PDF via GitHub Pages**](https://oliyad-oli.github.io/Web_Service/LAB_PRACTICE_1_REPORT_FINAL.pdf) | Direct browser-accessible PDF link |
| 📄 **Markdown Report** | [**LAB_PRACTICE_1_REPORT_FINAL.md**](LAB_PRACTICE_1_REPORT_FINAL.md) | Full 1,633-line report with 9 Mermaid diagrams and complete source code |
| 📘 **Instructor Lab Guide** | [**LAB PRACTICE 1 GUIDE AND EXPERIMENTS-7.pdf**](LAB%20PRACTICE%201%20GUIDE%20AND%20EXPERIMENTS-7.pdf) | Original assignment and guidelines from the instructor |

---

## 👥 Group Members

| # | Student Full Name | Student ID | Department |
|:---:|:---|:---:|:---:|
| 1 | **Yabets Tinsae Dube** | `1040/15` | Software Engineering |
| 2 | **Tesfalem Markos** | `0993/15` | Software Engineering |
| 3 | **Abdi Tamene Tekuma** | `0008/15` | Software Engineering |
| 4 | **Yosan Gonfa Merga** | `1084/15` | Software Engineering |
| 5 | **Oliyad Dandena** | `1786/15` | Software Engineering |

---

## 📋 Project Overview & Technology Stack

This project documents and implements the sixteen (16) practical experiments required for **Lab Practice 1** of the Web Services course. The laboratory focuses on configuring, administering, and deploying Java Web Services using the **Java Web Services Developer Pack (JWSDP)**, **Apache Tomcat 5.0**, **Apache Ant**, and **JAXR/UDDI** registry architecture.

* **Target Operating System:** Ubuntu Linux 24.04 LTS (x86_64)
* **Java Runtime:** OpenJDK 8 (`build 1.8.0_504-8u504-ga-1ubuntu1~24.04.3-b01`) configured in isolation via `tomcat50-jwsdp/bin/setenv.sh`
* **Servlet Container:** Apache Tomcat 5.0 (Sun Java System Web Services Pack 1.4)
* **Build System:** Apache Ant 1.10.14
* **Source Control:** GitHub (`https://github.com/Oliyad-oli/Web_Service.git`)

---

## 🧪 Comprehensive Experiments Summary (1–16)

| Exp # | Experiment Title | Key Coverage & Outputs |
|:---:|:---|:---|
| **1** | **Tomcat Startup & Console Output** | Terminal startup text, Catalina lifecycle, Linux background daemon architecture, and startup flow diagram. |
| **2** | **JAXR Registry Selection Dialog** | `jaxr-browser` Swing GUI, dropdown options (IBM, Microsoft, Local WSDP), and registry selection flowchart. |
| **3** | **Public vs. Private Intranet Registries** | Universal Business Registry (UBR) shutdown history (Dec 2005), internal corporate UDDI, and jUDDI alternatives. |
| **4** | **JAXR / UDDI Registry Browser Report** | 3-page deep dive: Organization, Primary Contact, Classifications (NAICS/UNSPSC), ServiceBinding, Submissions tab workflow, and UML class diagram. |
| **5** | **Tomcat Default Homepage** | `http://localhost:8080` layout, Jakarta mascot, Administration links, and Documentation structure. |
| **6** | **Tomcat Server Administration** | `admin/frameset.jsp` Struts interface, security roles, and JMX MBean live container configuration. |
| **7** | **Tomcat Web Application Manager** | `manager/html` dashboard, context lifecycle management (Start/Stop/Reload/Undeploy), and WAR deployment. |
| **8** | **Documentation Index** | `tomcat-docs/` user manual, 19 guide chapters, Servlet 2.4 and JSP 2.0 specifications. |
| **9** | **Application Developer's Guide** | Standard web application layout (`src/`, `web/`, `WEB-INF/`, `docs/`) by Craig R. McClanahan. |
| **10** | **General Purpose Ant Build Script** | `appdev/sample/build.xml` build targets (`prepare`, `compile`, `dist`, `install`, `reload`, `remove`). |
| **11** | **Security Manager HOW-TO** | Sandboxed JVM execution, `conf/catalina.policy` security permissions, and enterprise safety. |
| **12** | **Architectural Breakdown of `server.xml`** | Hierarchy of `<Server>`, `<Service>`, `<Connector>`, `<Engine>`, and `<Host>` with structural diagram. |
| **13** | **Webapps, Context Paths, WEB-INF & WAR** | 2-page analysis of application deployment model, private `WEB-INF`, classloading, and WAR packaging. |
| **14** | **Analysis of `webdav/WEB-INF/web.xml`** | Servlet 2.3 DTD specification, `WebdavServlet` servlet mapping, directory listings, and HTTP constraints. |
| **15** | **Apache Ant Build Architecture** | 4-page analysis of `<project>`, `<property>`, `<target>`, build lifecycle, and comparison with Maven & Gradle. |
| **16** | **Complete Ant Build, Servlet & Deployment** | End-to-end implementation of `MyServlet.java`, `web.xml`, automated Ant `build.xml`, compilation, deployment, and live verification at `http://localhost:8080/YourApp/hello`. |

---

## 📦 Repository Structure

```
Web_Service/
├── docs/                                  ← GitHub Pages deployment directory
│   ├── index.html                         ← Online interactive HTML report
│   ├── LAB_PRACTICE_1_REPORT_FINAL.pdf    ← Direct downloadable PDF report
│   └── LAB_GUIDE.pdf                      ← Original instructor lab manual
├── YourApp/                               ← Working Apache Ant Servlet project
│   ├── build.xml                          ← Automated compilation & deployment script
│   ├── src/com/example/MyServlet.java     ← Java Servlet source code
│   └── web/WEB-INF/web.xml                ← Servlet 2.3 deployment descriptor
├── tomcat50-jwsdp/                        ← Configured Apache Tomcat 5.0 JWSDP
│   ├── bin/setenv.sh                      ← Isolated Java 8 environment script
│   ├── conf/server.xml                    ← Server connector and engine configuration
│   ├── conf/tomcat-users.xml              ← Administrative credentials and roles
│   └── webapps/YourApp/                   ← Target servlet web application directory
├── Web_Services_Lab1_Group_Submission/    ← Standalone submission package
│   └── LAB_PRACTICE_1_REPORT_FINAL.pdf    ← Official report in submission archive
├── LAB_PRACTICE_1_REPORT_FINAL.pdf        ← Root official 31-page PDF report
├── LAB_PRACTICE_1_REPORT_FINAL.md         ← Root comprehensive markdown report
├── LAB_PRACTICE_1_REPORT_FINAL.html       ← Root standalone HTML report
└── README.md                              ← Repository documentation and index
```

---

## 🚀 How to Run & Verify Locally

### 1. Prerequisites
- **Ubuntu Linux** (or compatible POSIX environment)
- **OpenJDK 8**: Ensure `java -version` reports Java 1.8
- **Apache Ant 1.9+**: Ensure `ant -version` is available

### 2. Start the Tomcat 5.0 Server
```bash
cd tomcat50-jwsdp
./bin/startup.sh
```
Verify the server is running by opening: `http://localhost:8080/`

### 3. Build & Deploy the Servlet using Ant
```bash
cd ../YourApp
ant deploy
```
*Output snippet:*
```text
compile:
    [mkdir] Created dir: .../YourApp/build/classes
    [javac] Compiling 1 source file to .../YourApp/build/classes

prepare:
    [mkdir] Created dir: .../YourApp/build/WEB-INF/classes
     [copy] Copying 1 file to .../YourApp/build/WEB-INF/classes
     [copy] Copying 1 file to .../YourApp/build/WEB-INF

deploy:
    [mkdir] Created dir: .../tomcat50-jwsdp/webapps/YourApp
     [copy] Copying 2 files to .../tomcat50-jwsdp/webapps/YourApp
     [echo] Servlet deployed to .../tomcat50-jwsdp/webapps/YourApp. Restart or reload Tomcat to apply changes.

BUILD SUCCESSFUL
```

### 4. Test the Live Endpoint
Open your browser or run:
```bash
curl http://localhost:8080/YourApp/hello
```
**Expected Response:**
```text
Hello from MyServlet!
```

---

## 📜 Academic Integrity and Course Information
- **Institution:** Haramaya University
- **College:** College of Computing and Informatics
- **Department:** Department of Software Engineering
- **Course Code & Title:** SEng5071 — Web Services
- **Date:** October 2026
