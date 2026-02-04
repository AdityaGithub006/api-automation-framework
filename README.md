# API Automation Framework (Java + Rest Assured + TestNG + Allure)

Enterprise-grade API automation framework built with **Java 17**, **Rest Assured**, **TestNG**, and **Allure Reporting**.  
This framework is designed to demonstrate **real-world API test automation practices**, including environment handling, parallel execution, CI stability, and production-ready reporting.

---

## ✅ Why this framework stands out (Recruiter view)

- Clean, layered architecture (config → client → tests)
- Environment-based execution (`local`, `public`)
- Parallel execution enabled with TestNG + Maven Surefire
- CI-safe execution using Dockerized Swagger Petstore
- GitHub Actions pipeline with reliable startup & readiness checks
- Allure reporting with artifact upload and GitHub Pages support
- Designed to be readable, scalable, and interview-ready

---

## 🧱 Tech Stack

| Category | Technology |
|-------|-----------|
| Language | Java 17 |
| API Testing | Rest Assured |
| Test Runner | TestNG |
| Build Tool | Maven |
| Reporting | Allure |
| CI/CD | GitHub Actions |
| Test System | Swagger Petstore (Docker) |

---

## 📁 Project Structure

```
api-automation-framework/
├─ .github/
│  └─ workflows/
│     └─ api-tests.yml            # CI pipeline
├─ src/
│  └─ test/
│     ├─ java/
│     │  └─ com/aditya/api/
│     │     ├─ config/            # Environment & config loader
│     │     ├─ client/            # Base client + API clients
│     │     ├─ smoke/             # Smoke tests
│     │     └─ tests/             # Functional / E2E tests
│     └─ resources/
│        ├─ env/
│        │  ├─ local.properties
│        │  └─ public.properties
│        └─ allure/
│           ├─ environment.properties
│           └─ categories.json
├─ pom.xml
└─ README.md
```

---

## ⚙️ Configuration & Environment Handling

The framework uses **environment-based configuration** via JVM system property `env`.

### How it works
- Default environment: `local`
- Config files are loaded from:
  ```
  src/test/resources/env/<env>.properties
  ```

### Supported environments
- `local` → `env/local.properties`
- `public` → `env/public.properties`

### Example
```bash
mvn clean test -Denv=local
mvn clean test -Denv=public
```

If an environment file is missing, the framework **fails fast** with a clear error to prevent silent misconfiguration.

---

## 🚀 Running Tests Locally

### Prerequisites
- Java 17
- Maven 3.x
- Docker

### 1️⃣ Start Swagger Petstore locally
```bash
docker pull swaggerapi/petstore3:latest
docker run -d --name petstore -p 8080:8080 swaggerapi/petstore3:latest
```

Verify:
```bash
curl http://localhost:8080/api/v3/openapi.json
```

---

### 2️⃣ Run API tests
```bash
mvn clean test -Denv=local
```

---

## 🧪 Test Execution Strategy

### TestNG Groups
Tests are organized using **TestNG groups**.

Default group in `pom.xml`:
```xml
<groups>smoke</groups>
```

### Run smoke tests
```bash
mvn clean test -Denv=local -Dgroups=smoke
```

### Run all tests
```bash
mvn clean test -Denv=local -Dgroups=""
```

---

## ⚡ Parallel Execution

Parallel execution is enabled using Maven Surefire + TestNG:

```xml
<parallel>classes</parallel>
<threadCount>4</threadCount>
```

- Test classes run in parallel
- Improves execution speed
- Designed to avoid shared mutable state

---

## 📊 Allure Reporting

### Raw results
Generated automatically at:
```
target/allure-results
```

### Generate HTML report locally
```bash
mvn allure:report
```

Report output:
```
target/site/allure-maven-plugin
```

---

## 🤖 CI/CD with GitHub Actions

The CI pipeline is designed to be **stable and deterministic**.

### What the pipeline does
1. Checks out code
2. Sets up Java 17
3. Caches Maven dependencies
4. Starts Swagger Petstore inside the GitHub runner
5. Waits until API is reachable (prevents flakiness)
6. Executes API tests
7. Uploads Allure results as artifacts
8. (Optional) Publishes Allure HTML to GitHub Pages

### Key CI lesson implemented
> GitHub runners do **not** have your local Docker running —  
> the API must be started explicitly inside the workflow.

---

## 🌍 GitHub Pages (Allure Report)

Allure HTML reports can be published to **GitHub Pages**.

- Branch: `gh-pages`
- Folder: `/ (root)`

Once enabled, the report is accessible at:
```
https://<username>.github.io/api-automation-framework/
```

---

## 🛠 Common Issues & Solutions

### ❌ `Connection refused` in CI
**Cause:** Petstore not running inside GitHub runner  
**Solution:** Start Docker container + wait for readiness (already implemented)

---

### ❌ `ExceptionInInitializerError`
**Cause:** Missing environment file (e.g. `env/ci.properties`)  
**Solution:** Use only supported envs (`local`, `public`) or add config explicitly

---

### ❌ Tests pass locally but fail in CI
**Cause:** API not ready when tests start  
**Solution:** Explicit readiness check using `curl` before running tests

---

## 🔮 Future Enhancements

- Contract testing with OpenAPI schema validation
- Response time SLA assertions
- Retry strategy for idempotent APIs
- Test data builders & factories
- Docker Compose (API + tests)
- Separate smoke / regression pipelines

---

## 👤 Author

**Aditya Narayan**  
QA / SDET | API Automation | Java | Rest Assured | CI/CD  

GitHub:  
👉 https://github.com/AdityaGithub006

---

⭐ This framework demonstrates production-grade API automation practices, focusing on reliability, scalability, and maintainability rather than demo-level examples.  
It reflects how modern QA/SDET teams design, execute, and report API test automation in real-world delivery pipelines.
