# Spring Boot JWT Demo

![Java](https://img.shields.io/badge/Java-f89820?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)   
![JWT](https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?logo=gradle&logoColor=white)
![MySQL](https://img.shields.io/badge/SQL-4479A1?logo=mysql&logoColor=white) 
![GitHub](https://img.shields.io/badge/GitHub-black?logo=github)

---
This project is a **Spring Boot REST API** showcasing:
- 🔐 **Authentication & Authorization with JWT**  
- 🗄️ **Persistence with Spring Data JPA + MySQL**  
- 📦 **Layered architecture** (Controller, Service, Repository, DTO, Model)  
- ⚙️ **Build tools**: Maven & Gradle  

The goal is to demonstrate knowledge of backend development, database integration, and secure APIs.  

---

## 📂 Project Structure
```bash
src/main/java/com/example/demo
 ├─ config/       # Beans (PasswordEncoder, SecurityConfig)
 ├─ controller/   # REST Controllers
 ├─ dto/          # Data Transfer Objects
 ├─ model/        # JPA Entities
 ├─ repository/   # JpaRepository Interfaces
 ├─ service/      # Business Logic
 └─ security/     # JWT Filters & Utilities
 ```

## 🧪 Main Endpoints
POST /auth/login → Authenticate and return a JWT
POST /auth/register → Register a new user
GET /api/users → Get all users (requires token)

## How to Clone and Run

1. Clone the repository  
   ```bash
   git clone https://github.com/yourusername/springboot-jwt-demo.git
   cd springboot-jwt-demo
     ```

Before running this project, make sure you have the following installed:

- **Java 21** (JDK) → [Download here](https://adoptium.net/)  
- **Maven 3.9+** or **Gradle 8+** (project can run with either)  
- **MySQL 8.0+** (or another relational database, update `application.properties` accordingly)  
- **Git** for version control  

Optional (for easier development):
- **IntelliJ IDEA** or **VS Code** with Spring Boot extensions
- **Postman / cURL** for testing API endpoints
- **Docker** (if you want to run the database in a container)