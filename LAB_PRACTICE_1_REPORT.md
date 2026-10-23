# Web Services Lab Practice 1: Report and Experiments Guide
**Academic Year:** 2026–2027  
**Course:** Web Services (5th Year)  
**Document:** Lab Practice 1 Report  

---

## Student & Group Metadata
* **Group Name:** [INSERT GROUP NAME HERE]
* **Group Members Name and ID:**
  * Member 1: Oliad Dandena (ID: [INSERT ID])
  * Member 2: [INSERT MEMBER 2 NAME AND ID]
  * Member 3: [INSERT MEMBER 3 NAME AND ID]
* **Title of the Program:** Practical Configuration, Administration, and Deployment of Java Web Services (JWSDP, Apache Tomcat 5.0, JAXR/UDDI, and Apache Ant)
* **Target Operating System:** Ubuntu Linux 24.04 LTS (x86_64)

---

## Executive Summary & Environment Architecture
This laboratory practical focuses on the foundational toolchain for enterprise Java Web Services. Although the original lab manual references a legacy Windows environment, our implementation was deployed natively on **Ubuntu Linux** using a containerized compatibility architecture:
* **Java Runtime:** OpenJDK 8 (`build 1.8.0_504-8u504-ga-1ubuntu1~24.04.3-b01`) configured in isolation via `tomcat50-jwsdp/bin/setenv.sh`.
* **Servlet Container:** Apache Tomcat 5.0 (Sun Java System Web Services Pack 1.4).
* **Build System:** Apache Ant 1.10.14.
* **Source Control:** GitHub Repository (`https://github.com/Oliyad-oli/Web_Service.git`, branch `lab-one`).

---

## Detailed Experiments & Analysis

### Experiment 1: Tomcat Startup & Console Output
#### (a) Text Output Displayed on Console
Executing `./bin/startup.sh` (or `./bin/catalina.sh run`) boots the Catalina servlet engine:
```text
Using CATALINA_BASE:   /home/oliyad-dandena/Documents/5thYear/web_services/tomcat50-jwsdp
Using CATALINA_HOME:   /home/oliyad-dandena/Documents/5thYear/web_services/tomcat50-jwsdp
Using CATALINA_TMPDIR: /home/oliyad-dandena/Documents/5thYear/web_services/tomcat50-jwsdp/temp
Using JAVA_HOME:       /usr/lib/jvm/java-8-openjdk-amd64
INFO: Installing web application at context path /webdav from URL file:.../webapps/webdav
INFO: Installing web application at context path /jsp-examples from URL file:.../webapps/jsp-examples
INFO: Installing web application at context path /servlets-examples from URL file:.../webapps/servlets-examples
INFO: Installing web application at context path /tomcat-docs from URL file:.../webapps/tomcat-docs
INFO: Installing web application at context path  from URL file:.../webapps/ROOT
INFO: Starting Coyote HTTP/1.1 on port 8080
INFO: Server startup in 993 ms
```

#### (b) Dialog Box & Process Lifecycle
* **On Microsoft Windows:** Executing `startup.bat` opens a separate Command Prompt dialog titled `"Tomcat"` that holds the JVM foreground thread.
* **On Ubuntu Linux:** The startup script creates an isolated background daemon process (`org.apache.catalina.startup.Bootstrap start`), piping logs to `logs/server.log` and freeing the terminal session.

---

### Experiment 2: The JAXR Registry Selection Dialog
* **Command:** `jaxr-browser` (located in JWSDP `jaxr/bin`).
* **Dialog Interface:**
  * When initiated, a Java Swing modal window titled **"Select Registry"** appears.
  * It provides a drop-down menu containing registry providers:
    1. *IBM UDDI Test Registry*
    2. *Microsoft UDDI Test Registry*
    3. *Local Java WSDP Registry Server* (`http://localhost:8080/registry-server/RegistryServerServlet`)
  * Choosing the local registry allows administrators to interact with the UDDI service deployed directly inside Apache Tomcat.

---

### Experiment 3: Public Registries vs. Private Intranet Registries
* **Analysis:** The lab guide references external URLs `http://uddi.microsoft.com` and `http://www-3.ibm.com/services`.
* **Findings:** In December 2005, IBM, Microsoft, and SAP mutually announced the closure of the public Universal Business Registry (UBR) nodes due to spam and lack of business utility. In modern Web Services architecture, UDDI and registry services operate as **internal enterprise repositories** (e.g., Apache jUDDI or JWSDP local registry) within corporate intranets.

---

### Experiment 4: Comprehensive Analysis of the JAXR Registry Browser
The Java API for XML Registries (JAXR) provides a standard API for accessing business registries:
1. **Organization (`Organization`):** The top-level corporate or business entity publishing services (contains name, description, unique key).
2. **Primary Contact (`User` / `PostalAddress`):** Details of the administrative or technical personnel managing the service.
3. **Classifications (`Classification`):** Standardized taxonomy keys (such as NAICS or UNSPSC industry categories) enabling consumers to discover related services.
4. **Service Binding (`ServiceBinding`):** The technical specification containing the **Access URI** (endpoint address) and WSDL tModel references.
5. **Submissions Tab:** An authenticated management interface enabling developers to create, update, or remove registered web services.

---

### Experiment 5: Tomcat Default Homepage (`http://localhost:8080`)
* **Page Title:** `Sun-Java-System/Web-Services-Pack-1.4`.
* **Visual Elements:**
  * Apache Tomcat mascot (*"The Mighty Tomcat - MEOW!"*) on the left.
  * Jakarta Project banner on the right.
* **Functional Sections:**
  * *Administration:* Direct access to Status, Tomcat Administration, and Tomcat Manager.
  * *Documentation:* Release Notes and complete Tomcat 5.0 technical manuals.
  * *Examples:* Live interactive Servlet and JSP demonstration suites.

---

### Experiment 6: Tomcat Server Administration (`http://localhost:8080/admin/frameset.jsp`)
* Renders the Struts-based **Tomcat Server Administration Login Page** (`admin/login.jsp`).
* Requires administrative credentials (`role="admin"`).
* Provides dynamic JMX MBean configuration of server connectors, virtual hosts, and thread pools without manual file editing.

---

### Experiment 7: Tomcat Web Application Manager (`http://localhost:8080/manager/html`)
* Enforces HTTP Basic Authentication (`realm="Tomcat Manager Application"`).
* **Dashboard Features:**
  * Lists all deployed contexts (`/ROOT`, `/balancer`, `/jsp-examples`, `/servlets-examples`, `/tomcat-docs`, `/webdav`, `/YourApp`).
  * Real-time lifecycle controls: **Start**, **Stop**, **Reload**, and **Undeploy**.
  * Dynamic deployment form for uploading and unpacking WAR files remotely.

---

### Experiment 8: Documentation Index (`http://localhost:8080/tomcat-docs/`)
* Comprehensive technical index featuring 19 user guide chapters:
  * Setup, Deployment, Security Realms, Security Manager, JNDI DataSources, Classloading, SSL/TLS, Clustering, and Load Balancing.
  * API specifications for Servlet 2.4 and JSP 2.0.

---

### Experiment 9: Application Developer's Guide (`http://localhost:8080/tomcat-docs/appdev/index.html`)
* Authored by Craig R. McClanahan (creator of Struts).
* Defines standard web application layout:
  * `src/`: Java source code.
  * `web/`: Static HTML, CSS, JSPs, and `WEB-INF/`.
  * `docs/`: Developer documentation.

---

### Experiment 10: General Purpose Build Script (`http://localhost:8080/tomcat-docs/appdev/sample/build.xml`)
* Demonstrates Ant-based continuous integration for Tomcat 5:
  * Tasks: `prepare`, `compile`, `dist`, and Catalina custom tasks (`install`, `reload`, `remove`).

---

### Experiment 11: Security Manager HOW-TO (`http://localhost:8080/tomcat-docs/security-manager-howto.html`)
* Explains sandboxed execution using Java's `SecurityManager`.
* Configured via `conf/catalina.policy` to restrict untrusted servlets from reading system files or invoking native system calls.

---

### Experiment 12: Architectural Breakdown of `conf/server.xml`
The core configuration follows an object hierarchy:
* **`<Server>`**: Root process container listening on port `8005`.
* **`<GlobalNamingResources>`**: Shared JNDI data sources and user databases.
* **`<Service>`**: Groups connectors with a processing engine.
* **`<Connector port="8080">`**: HTTP/1.1 network socket handler.
* **`<Engine name="Catalina">`**: Internal request-processing pipeline.
* **`<Host name="localhost" appBase="webapps">`**: Virtual host deployment directory.

---

### Experiment 13: Webapps, Context Paths, `WEB-INF`, and WAR Files
* **`webapps/`:** The application directory where projects are automatically discovered and deployed.
* **Context Path:** The URL path segment mapping requests to an application (e.g., `/YourApp`).
* **`WEB-INF/` Directory:** Private directory forbidden from direct client access. Contains `web.xml`, compiled classes (`classes/`), and JAR libraries (`lib/`).
* **Web ARchive (WAR):** Packaged ZIP archive allowing atomic, zero-downtime deployment.

---

### Experiment 14: Analysis of `webdav/WEB-INF/web.xml`
* Defined under Servlet 2.3 DTD (`http://java.sun.com/dtd/web-app_2_3.dtd`).
* Maps `org.apache.catalina.servlets.WebdavServlet` to URL pattern `/*`.
* Configures directory listing parameters and optional HTTP Basic authentication constraints.

---

### Experiment 15: Apache Ant Build Architecture
Apache Ant is a cross-platform, XML-based build automation tool:
1. **`<project>` Element:** Root element defining project name, default target, and base directory.
2. **`<property>` Tags:** Immutable key-value definitions referenced via `${property.name}`.
3. **`<target>` Elements:** Sequential execution blocks supporting dependency resolution via `depends="..."`.
4. **Core Tasks:** `<mkdir>`, `<delete>`, `<javac>`, `<copy>`, `<jar>`, `<echo>`.
5. **Command-Line Invocation:**
   * `ant`: Executes default target.
   * `ant <target>`: Executes specified build step.

---

### Experiment 16: Complete Ant Build, Servlet Compilation & Deployment

#### 1. Project Source Code: `YourApp/src/com/example/MyServlet.java`
```java
package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello from MyServlet!");
    }
}
```

#### 2. Deployment Descriptor: `YourApp/web/WEB-INF/web.xml`
```xml
<?xml version="1.0" encoding="ISO-8859-1"?>
<!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
    "http://java.sun.com/dtd/web-app_2_3.dtd">
<web-app>
    <display-name>YourApp Sample Web Application</display-name>
    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.example.MyServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

#### 3. Apache Ant Build Script: `YourApp/build.xml`
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project name="SimpleServletBuild" default="deploy" basedir=".">
    <property name="src.dir" value="src"/>
    <property name="build.dir" value="build"/>
    <property name="web.dir" value="web"/>
    <property name="tomcat.home" location="${basedir}/../tomcat50-jwsdp"/>
    <property name="deploy.dir" location="${tomcat.home}/webapps/YourApp"/>

    <path id="compile.classpath">
        <pathelement location="${tomcat.home}/common/lib/servlet-api.jar"/>
    </path>

    <target name="clean">
        <delete dir="${build.dir}"/>
    </target>

    <target name="compile" depends="clean">
        <mkdir dir="${build.dir}/classes"/>
        <javac srcdir="${src.dir}" destdir="${build.dir}/classes" includeantruntime="false"
               classpathref="compile.classpath" source="1.8" target="1.8"/>
    </target>

    <target name="prepare" depends="compile">
        <mkdir dir="${build.dir}/WEB-INF/classes"/>
        <copy todir="${build.dir}/WEB-INF/classes">
            <fileset dir="${build.dir}/classes"/>
        </copy>
        <copy todir="${build.dir}/WEB-INF">
            <fileset dir="${web.dir}/WEB-INF"/>
        </copy>
    </target>

    <target name="deploy" depends="prepare">
        <mkdir dir="${deploy.dir}"/>
        <copy todir="${deploy.dir}">
            <fileset dir="${build.dir}">
                <exclude name="classes/**"/>
            </fileset>
        </copy>
        <echo message="Servlet deployed to ${deploy.dir}. Restart or reload Tomcat to apply changes."/>
    </target>
</project>
```

#### 4. Compilation & Deployment Execution Output
```text
Buildfile: /home/oliyad-dandena/Documents/5thYear/web_services/YourApp/build.xml

clean:

compile:
    [mkdir] Created dir: /home/oliyad-dandena/Documents/5thYear/web_services/YourApp/build/classes
    [javac] Compiling 1 source file to /home/oliyad-dandena/Documents/5thYear/web_services/YourApp/build/classes

prepare:
    [mkdir] Created dir: /home/oliyad-dandena/Documents/5thYear/web_services/YourApp/build/WEB-INF/classes
     [copy] Copying 1 file to /home/oliyad-dandena/Documents/5thYear/web_services/YourApp/build/WEB-INF/classes
     [copy] Copying 1 file to /home/oliyad-dandena/Documents/5thYear/web_services/YourApp/build/WEB-INF

deploy:
    [mkdir] Created dir: /home/oliyad-dandena/Documents/5thYear/web_services/tomcat50-jwsdp/webapps/YourApp
     [copy] Copying 2 files to /home/oliyad-dandena/Documents/5thYear/web_services/tomcat50-jwsdp/webapps/YourApp
     [echo] Servlet deployed to /home/oliyad-dandena/Documents/5thYear/web_services/tomcat50-jwsdp/webapps/YourApp. Restart or reload Tomcat to apply changes.

BUILD SUCCESSFUL
Total time: 0 seconds
```

#### 5. Live Runtime Verification
* **Endpoint URL:** `http://localhost:8080/YourApp/hello`
* **HTTP Status:** `200 OK`
* **Response Body:** `Hello from MyServlet!`
