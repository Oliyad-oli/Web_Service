# 🎓 Web Services — Lab Practice 1
**Haramaya University | College of Computing and Informatics | Department of Software Engineering**  
**Academic Year:** 2026–2027 | **Year V – Semester I** | **Course:** Web Services (SEng5071)

---

## 🌐 Live Report (GitHub Pages)
> **📄 View the full Lab Report online:**  
> **https://oliyad-oli.github.io/Web_Service/**

---

## 👥 Group Members

| # | Full Name | Student ID |
|---|-----------|------------|
| 1 | Yabets Tinsae Dube | 1040/15 |
| 2 | Tesfalem Markos | 0993/15 |
| 3 | Abdi Tamene Tekuma | 0008/15 |
| 4 | Yosan Gonfa Merga | 1084/15 |
| 5 | Oliyad Dandena | 1786/15 |

---

## 📦 Repository Structure

```
Web_Service/
├── docs/                          ← GitHub Pages (live website)
│   ├── index.html                 ← Full Lab Report (HTML, browsable online)
│   └── LAB_GUIDE.pdf              ← Instructor's Lab Guide PDF
├── YourApp/                       ← Apache Ant project
│   ├── build.xml                  ← Automated build script
│   ├── src/com/example/
│   │   └── MyServlet.java         ← Java Servlet source
│   └── web/WEB-INF/
│       └── web.xml                ← Deployment descriptor
├── tomcat50-jwsdp/                ← Tomcat 5.0 (JWSDP bundled)
│   ├── bin/setenv.sh              ← Java 8 runtime isolation
│   ├── conf/server.xml            ← Tomcat configuration
│   └── webapps/YourApp/           ← Deployed servlet
├── LAB_PRACTICE_1_REPORT_FINAL.md ← Full Markdown report (16 experiments)
├── LAB_PRACTICE_1_REPORT_FINAL.html ← HTML version
└── README.md                      ← This file
```

---

## 🧪 Experiments Covered (1–16)

| # | Experiment | Topic |
|---|------------|-------|
| 1 | Tomcat Startup | Console output & process lifecycle |
| 2 | JAXR Registry Browser | Dialog box & registry selection |
| 3 | Public UDDI Registries | IBM/Microsoft test registries |
| 4 | JAXR/UDDI Deep Dive | Organization, Contact, Classifications, ServiceBinding |
| 5 | localhost:8080 | Tomcat default homepage |
| 6 | /admin/frameset.jsp | Tomcat Administration panel |
| 7 | /manager/html | Web Application Manager |
| 8 | /tomcat-docs/ | Documentation index |
| 9 | /appdev/index.html | Application Developer's Guide |
| 10 | /appdev/sample/build.xml | General purpose Ant build script |
| 11 | security-manager-howto.html | Java SecurityManager |
| 12 | server.xml | Tomcat configuration hierarchy |
| 13 | webapps/, WEB-INF, WAR | Web application structure |
| 14 | webdav/WEB-INF/web.xml | WebDAV configuration |
| 15 | Apache Ant | Build automation (4-page report) |
| 16 | Servlet Build & Deploy | Full Ant build + live verification |

---

## 🚀 How to Run

### Start Tomcat Server
```bash
cd tomcat50-jwsdp
./bin/startup.sh
# Open: http://localhost:8080/
```

### Build & Deploy Servlet
```bash
cd YourApp
ant deploy
# Open: http://localhost:8080/YourApp/hello
# Expected: Hello from MyServlet!
```

### Environment
- **OS:** Ubuntu Linux 24.04 LTS
- **Java:** OpenJDK 8 (configured via `tomcat50-jwsdp/bin/setenv.sh`)
- **Tomcat:** 5.0 (Sun JWSDP 1.4)
- **Ant:** 1.10.14
