# OrangeHRM Test Automation Framework 🚧 *(In Development)*

An automated UI testing framework built with **Java**, **Selenium WebDriver**, and **TestNG** targeting the [OrangeHRM](https://opensource-demo.orangehrmlive.com/) web application.

> **Status:** 🟡 Active Development — Test scenarios, Page Object Model classes, and reporting utilities are currently being implemented and expanded.

---

## 🚀 Tech Stack & Tools

* **Language:** Java
* **UI Automation:** Selenium WebDriver
* **Test Runner:** TestNG
* **Design Pattern:** Page Object Model (POM)
* **Build Tool:** Apache Maven
* **Version Control:** Git & GitHub

---

## 📌 Features & Implemented Modules

* **Page Object Model (POM):** Clean separation of page locators, actions, and test verification scripts.
* **Authentication Module:** Automated test suites for valid/invalid login flows and session management.
* **Dynamic Web Interactions:** Explicit and fluent waits to handle dynamic AJAX elements and page loads.
* **Cross-Browser Support:** Configured for automated execution across Chrome and Firefox.
* **Test Reporting:** Standard TestNG suite execution reports with detailed pass/fail logs.

---

## 📂 Project Structure

```text
OrangeHRMProject/
├── src/
│   ├── main/java/com/orangehrm/
│   │   ├── base/            # Base test setup, driver initialization, and tear down
│   │   ├── pages/           # Page Object classes (LoginPage, DashboardPage, etc.)
│   │   └── utils/           # Utilities, wait handlers, and configuration readers
│   └── test/java/com/orangehrm/tests/
│       └── ...              # TestNG test cases
├── src/test/resources/      # Test data, locators, and test properties
├── testng.xml               # Test execution suite configuration
└── pom.xml                  # Maven dependencies and build plugins
