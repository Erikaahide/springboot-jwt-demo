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
src/main/java
 ├─ config/       # Beans (PasswordEncoder, SecurityConfig)
 ├─ controller/   # REST Controllers
 ├─ dto/          # Data Transfer Objects
 ├─ model/        # JPA Entities
 ├─ repository/   # JpaRepository Interfaces
 ├─ service/      # Business Logic
 ├─ security/     # JWT Filters & Utilities
 └─ DemoEaApplication.java  # Main class
 ```

This is a demo Spring application for a simple fitness social network where users can register, log in, and create posts.

The project includes:
- schema.sql → Database schema (executed on startup).
- data.sql → Initial test data.
- Local insertion → You can also insert data manually via Postman or SQL client.
- DDL auto → spring.jpa.hibernate.ddl-auto=update (tables are updated automatically).

## 🧪 Main Endpoints
- POST /auth/login → Authenticate and return a JWT
- POST /auth/register → Register a new user
- GET /api/users → Get all users (requires token)

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

- Create the database in MySQL CREATE DATABASE fitnet;
- You can load default test data via data.sql.
- If you prefer to test manually, use Postman with the examples above.

## API Endpoints

**Register User**
POST http://localhost:8080/auth/register
Content-Type: application/json
Response:
{ "token": "..." }

**Login**
POST http://localhost:8080/auth/login
Content-Type: application/json
{
  "email": " ",
  "password": " "
}
Response includes a JWT token.

**Create Post (Authenticated)**
POST http://localhost:8080/api/posts
Authorization: Bearer <token>
Content-Type: application/json
{
  "content": "New post"
}

**List Posts (READ)**
GET http://localhost:8080/api/posts?page=0&size=5
Authorization: Bearer <token>

**Update Post**
PUT http://localhost:8080/api/posts/{id}
Authorization: Bearer <token>
Content-Type: application/json
{
  "content": "Edit"
}
Only the author of the post can update it.

**Delete Post**
DELETE http://localhost:8080/api/posts/{id}
Authorization: Bearer <token>
If successful → 204 No Content
Only the author of the post can delete it.