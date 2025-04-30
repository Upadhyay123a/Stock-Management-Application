# 📌 Stock Management Application  
**Java | Spring Boot | Maven | REST API | H2 Database**  

--- n  n  n  
## 📖 Overview  
The **Stock Management Application** is a **Spring Boot-based CRUD** application designed to efficiently manage stocks. It allows users to **add, update, retrieve, and delete stock data** while ensuring seamless database interaction.  

This document provides insights into the **application’s architecture, endpoints, data flow, and database configuration** to help developers and stakeholders understand its functionality.  

---

## 📌 Table of Contents  
1. [Technologies Used](#-technologies-used)  
2. [Dependencies](#-dependencies)  
3. [Architecture & Data Flow](#-architecture--data-flow)  
4. [API Endpoints](#-api-endpoints)  
5. [Database Schema](#-database-schema)  
6. [Usage & Setup](#-usage--setup)  
7. [License](#-license)  
8. [Contact](#-contact)  

---

## 🛠 Technologies Used  
- **Framework:** Spring Boot  
- **Language:** Java 21  
- **Build Tool:** Apache Maven 4.0.0  
- **Database:** H2 (In-memory) or MySQL  
- **Validation:** Spring Boot Starter Validation  

---

## 📌 Dependencies  

### 1️⃣ Spring Boot Starter Data JPA  
**Purpose:** Simplifies database operations with Spring Data repositories.  
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>


2️⃣ Spring Boot Starter Validation
Purpose: Provides built-in validation mechanisms for request data.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>


3️⃣ Spring Boot Starter Web
Purpose: Enables REST API development and web-based functionality.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>



4️⃣ H2 Database (For Testing & Development)
Purpose: Lightweight, in-memory database for development.

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>




5️⃣ Lombok (Optional – Reduces Boilerplate Code)


<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>



6️⃣ Spring Boot Maven Plugin
Purpose: Helps in building and packaging Spring Boot applications.

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>


📌 Architecture & Data Flow
This project follows the MVC (Model-View-Controller) architecture:

📌 Controller Layer

Handles incoming HTTP requests and forwards them to the service layer.
Example: StockController.java

📌 Service Layer

Contains the business logic and interacts with the repository layer.
Example: StockService.java

📌 Repository Layer

Communicates with the database using Spring Data JPA.
Example: StockRepository.java

📌 Model (Entity Layer)

Represents database tables as Java objects.
Example: Stock.java

📌 API Endpoints

1️⃣ Get Stocks by Type
Method: GET
Endpoint: /stocks/by-type/{stockType}
Example: /stocks/by-type/ENERGY


2️⃣ Get Stocks Above Price & Lower Date

Method: GET
Endpoint: /stocks/abovePrice/price/{price}/lowerDate/date/{date}
Example: /stocks/abovePrice/price/100/lowerDate/date/2023-01-01T00:00:00


3️⃣ Insert Stocks

Method: POST
Endpoint: /stocks
Request Body: JSON list of stock objects.


4️⃣ Update Stock Market Cap

Method: PUT
Endpoint: /stocks/marketCap/{marketCap}/id/{id}
Example: /stocks/marketCap/500.0/id/1


5️⃣ Delete Stocks by Owner Count

Method: DELETE
Endpoint: /stocks/ownerCount/{count}
Example: /stocks/ownerCount/5


📌 Database Schema
Column Name	           Data Type	            Description
stockId (Primary)	INT (Auto Increment)	Unique stock identifier
stockName (Unique)	VARCHAR(255)	        Stock name
stockPrice	          DOUBLE	            Price of the stock
stockOwnerCount	      INT	                Number of stock owners
stockType	         ENUM	                Type of stock (e.g., ENERGY, TECH)
stockMarketCap	     DOUBLE	                Market capitalization of the stock
stockBirthTimeStamp	 DATETIME	            Timestamp of stock creation


📌 Database Configuration (H2 for Local Development)
Modify application.properties for H2:

properties


spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
To access the H2 Console:

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (Leave blank)


📌 Usage & Setup

1️⃣ Prerequisites
Ensure you have:
Java 21
Maven 4.0.0
Spring Boot


2️⃣ Build & Run

Option 1: Using Maven
mvn clean install
mvn spring-boot:run


Option 2: Using JAR File
mvn package
java -jar target/Stock-Management-Application-0.0.1-SNAPSHOT.jar

📌 License
This project is licensed under the BSD 3-Clause License.

📌 Contact

For questions, feedback, or contributions, reach out to:
Atul Upadhyay
📧 Email: atulupa@12345@gmail.com
🔗 GitHub:(https://github.com/Upadhyay123a/)

