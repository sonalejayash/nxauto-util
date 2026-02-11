# NX Auto Utility (nxauto-util)

![Java](https://img.shields.io/badge/Java-8+-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.6+-brightgreen.svg)
![Build](https://img.shields.io/badge/Build-Passing-success.svg)
![CI](https://img.shields.io/badge/CI-Jenkins-blue.svg)
![License](https://img.shields.io/badge/License-MIT-lightgrey.svg)

**NX Auto Utility** is a lightweight Java CLI tool designed to **validate, process, and orchestrate NX / TCIN-style test artifacts** in a **CI/CD-friendly, DevOps-aligned workflow**.

This project modernizes legacy TCIN execution patterns by enforcing **strict workspace validation**, **deterministic input handling**, and **pipeline-safe execution semantics**.

---

## 🔍 Why This Project Exists

Traditional TCIN execution relies heavily on:
- Manual directory preparation  
- Imperative batch / Perl scripts  
- Tight coupling between test logic and environment  

This utility introduces:
- **Predictable workspace contracts**
- **Automated validation**
- **CI-first execution**
- **Clear failure signaling**

It is intentionally minimal and extensible.

---

## 🧱 Architecture Overview

+--------------------+
| Jenkins / CLI      |
| (java -jar)        |
+---------+----------+
          |
          v
+--------------------+
| NXAutoUtil (Main)  |
+---------+----------+
          |
          +---------------------+
          |                     |
          v                     v
+--------------------+   +------------------------+
| PathValidator      |   | XMLArtifactProcessor   |
| - workspace check  |   | - scans input/*.xml    |
| - input validation |   | - processes artifacts  |
+--------------------+   +------------------------+
          |
          v
+--------------------+
| ExitCode / Logging |
+--------------------+


## 📁 Expected Workspace Structure

<workspace>
└── nxutil
    └── <execution-id>
        └── input
            ├── testcase.xml
            └── *.xml



If this structure is violated, execution **fails fast** with a clear error.

---

## ▶️ How to Run

### Build
```bash
mvn clean package
Execute
java -jar target/nxauto-util.jar <workspace-path> <execution-id>
Example
java -jar target/nxauto-util.jar C:\temp\nx-workspace jenkins-run
✅ What the Tool Does
Validates workspace and execution directories

Ensures required input folders exist

Discovers XML test artifacts

Processes each artifact deterministically

Emits clear SUCCESS / FAILURE status

Returns non-zero exit code on failure (CI-safe)

🧪 Test Coverage
Path validation logic

Missing directory handling

XML artifact discovery

Failure propagation

Tests are written using JUnit 4 and run automatically during Maven builds.

🚦 CI/CD Integration
This project is designed to run unchanged in Jenkins.

Typical pipeline stages:

Checkout

Maven build & test

Workspace preparation

CLI execution

Status evaluation

🛣️ Roadmap
Planned extensions (intentionally out of current scope):

JSON-driven test definitions

Legacy TCIN file generation

NX Open / NXAutoUtil integration

Structured execution reports

Parallel test orchestration

👤 Author
Jayash Sonale
DevOps / CI-CD / Platform Reliability
