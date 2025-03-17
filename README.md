# user_managment_System


### **📌 User Management System**

🚀 A **User Management System** built using **Spring Boot**, **Spring Security**, and **JWT Authentication**.  
This project provides **user authentication, role-based access control (RBAC), and API security**.

---

## **📂 Features**
✅ **User Registration & Authentication** (JWT-based)  
✅ **Role-Based Access Control (RBAC)** (Admin, User, etc.)  
✅ **Secure REST APIs with Spring Security**  
✅ **Database Integration (MySQL)**  
✅ **API Documentation with OpenAPI (Swagger UI)**  
✅ **Audit Logging for Security Events**

---

## **🛠️ Tech Stack**
- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Token)
- **API Documentation:** Swagger (SpringDoc OpenAPI)

---

## **🚀 Installation & Setup**
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/seifElsokary2003/user_managment_System.git
cd user_managment_System
```

### **2️⃣ Configure the Database**
Update the **`src/main/resources/application.properties`** file with your MySQL credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/user_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

### **3️⃣ Build & Run the Project**
```sh
mvn spring-boot:run
```

### **4️⃣ Access the API**
- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **User Authentication Endpoints:** `/api/auth/**`
- **Admin Endpoints:** `/api/users/all` (Requires Admin Role)

---

## **🔐 Authentication & Authorization**
- **Login to get JWT Token:**  
  `POST /api/auth/login`
- **Use JWT Token in Requests:**  
  Add the token to the `Authorization` header:
  ```
  Authorization: Bearer your-jwt-token
  ```

---

## **🛠️ Contributing**
1. Fork the repo
2. Create a new branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m "Add new feature"`
4. Push to the branch: `git push origin feature-name`
5. Open a Pull Request

---

## **📜 License**
This project is open-source and available under the **MIT License**.

---

### **📧 Contact**

📧 seifelsokary2023@gmail.com


If you have any questions, feel free to reach out! 🚀