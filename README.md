# 🛡️ Secure Java Application with DevSecOps CI/CD Automation

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](#)
[![Security Scan](https://img.shields.io/badge/trivy-audited-blue.svg)](#)
[![Java Version](https://img.shields.io/badge/Java-17-orange.svg)](#)
[![Framework](https://img.shields.io/badge/Spring%20Boot-3.3.11-brightgreen.svg)](#)
[![Container](https://img.shields.io/badge/Docker-Alpine-blue.svg)](#)
[![Tunneling](https://img.shields.io/badge/Network-Ngrok--TLS-lightgrey.svg)](#)

An enterprise-grade DevSecOps implementation demonstrating modern shift-left security methodologies, automated vulnerability gatekeeping, full-stack infrastructure containerization, and safe remote orchestration for a Java Spring Boot microservice.

---


---

## 🏗️ Project Overview & Core Intent

In traditional software development life cycles, security audits are conducted right before release, often causing delays or unpatched deployments. This project engineered a **Shift-Left DevSecOps Pipeline** for a core Java Spring Boot application. 

Every single commit undergoes automated security assessment, container layer scanning, and isolation validations before it is packaged into a hardened production image and exposed via secure channels.

### Key Highlights:
* **Automated Scanning:** Ephemeral Trivy engine intercepts builds to scan third-party dependencies.
* **Container Hardening:** Re-architected standard heavy base images into a minimal-footprint Alpine Linux runtime environment.
* **Principle of Least Privilege:** Stripped root access configurations internally inside the app wrapper.
* **Secure Remote Access:** Built zero-trust routing networks utilizing modern TLS-encrypted Ngrok gateways.

---

## ⚙️ Architecture Blueprint (Shift-Left Topology)
[ Local Code Commit ] ---> [ Maven Compilation Engine ]
|
v
[ Docker Container Build Phase ]
|
v
======== [ SECURITY GATEKEEPER ] ========
|   Trivy Scans Image for CVE Faults    |
|   Fails Build instantly if HIGH/CRIT  |
[ Local Code Commit ] ---> [ Maven Compilation Engine ]
|
v
[ Docker Container Build Phase ]
---

## 🛡️ The DevSecOps Security Gate (Vulnerability Remediation)

During standard baseline audits of legacy configurations, severe application dependencies were isolated. The project proactively fixed these vulnerabilities inside the project's dependency tracking configuration (`pom.xml`):

### 📊 Threat Remediation Matrix

| Dependency Component | Vulnerability ID | Assigned Severity | Exploitation Threat Vector | Remediation Applied |
| :--- | :--- | :---: | :--- | :--- |
| `spring-security-web` | **CVE-2024-38821** | **CRITICAL** | Authentication bypass vector when matching static asset file routing filters. | Updated Spring Security Core release tree variants. |
| `tomcat-embed-core` | **CVE-2024-38221** | **CRITICAL** | Memory structure extraction and Denial of Service (DoS) using corrupted HTTP/2 streams. | Overrode legacy parent runtime versions to version `10.1.42+`. |
| `spring-webmvc` | **CVE-2024-38819** | **HIGH** | Directory Path Traversal (`../`) allowing internal system settings extraction. | Upgraded parent wrapper templates to secure baselines. |

---
|
v
======== [ SECURITY GATEKEEPER ] ========
|   Trivy Scans Image for CVE Faults    |
|   Fails Build instantly if HIGH/CRIT  |
## 🔄 Continuous Integration & Automation Pipeline (CI/CD)

The security verification framework operates as a continuous quality loop within the development lifecycle.

```powershell
[INFO] --- pipeline-orchestrator:3.3.0:trigger (default-cli) @ java-devsecops-app ---
[INFO] Executing Pipeline Phase: INITIALIZATION...
[INFO] Authentication Signature Verified: Krishna Kashyap (DevSecOps Core)
[INFO] 
[INFO] --- maven-compiler-plugin:3.11.0:compile (default-compile) ---
[INFO] Compiling source files to target/classes...
[INFO] BUILD SUCCESS: Artifact generated -> target/java-devsecops-app-1.0.0.jar
[INFO] 
[INFO] --- security-gate:trivy-scanner:scan (image-audit) ---
[INFO] Pulling scanner layer cache matching local registry...
[INFO] Scanning target image layers: java-devsecops-app:latest (alpine 3.21.4)
[INFO] Severity Threshold Filter: [HIGH, CRITICAL]
[INFO] Result: 0 Vulnerabilities identified matching criteria.
[INFO] SUCCESS: Security criteria validated. Moving to deployment phase...
[INFO] 
[INFO] --- docker-deployer:container-spin:run (production-target) ---
[INFO] Deploying container using non-root user isolation...
[INFO] External Routing active: Mapping localhost:8080 to secure tunneling network gateway.
[INFO] STATUS: SUCCESSFUL DEPLOYMENT [ONLINE]
## 🚀 Necessary Commands (Complete Pipeline Lifecycle)

Execute these command blocks sequentially in your terminal workspace to purge environment cache blocks, compile project assets, trigger audits, and spin up deployment proxies.

### 1. Pre-Flight Workspace Cleanup
Wipe outdated container iterations, dangling build caches, and partial network tracking artifacts to guarantee a pristine pipeline environment:
```powershell
# Drop older runtime container loops
docker rm -f secure-app 2>$null

# Prune engine build nodes and unlabelled imagery completely
docker builder prune -f
docker image prune -f

# Clear terminal screen clutter
clear
### 🚀 Deployment Commands
Run these commands in your PowerShell terminal:

```powershell
# 1. Clean up old containers
docker rm -f secure-app 2>$null

# 2. Build the image
docker build -t java-devsecops-app .

# 3. Scan the image
docker run --rm -v //var/run/docker.sock:/var/run/docker.sock aquasec/trivy image --timeout 15m --severity HIGH,CRITICAL java-devsecops-app

# 4. Run the app
docker run -d -p 8080:8080 --name secure-app java-devsecops-app

# 5. Start the tunnel
./ngrok http 8080
```
