<h1 align="center">Stock Management Application</h1>
<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java-21-darkblue.svg" />
</a>
<a href="Maven url">
    <img alt="Maven" src="https://img.shields.io/badge/Maven-4.0-brightgreen.svg" />
</a>
<a href="Spring Boot url">
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.5.1-brightgreen.svg" />
</a>
<a href="MySQL url">
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-8.0-blue.svg" />
</a>
<a href="License url">
    <img alt="BSD Clause 3" src="https://img.shields.io/badge/License-BSD_3--Clause-blue.svg"/>
</a>
</p>

---

## Overview

The **Stock Management Application** is a comprehensive Spring Boot-based CRUD (Create, Read, Update, Delete) application designed for managing stock inventory. This application provides RESTful APIs to add, update, retrieve, and delete stock information efficiently. Built with Spring Boot 3.5.1 and MySQL database, it ensures robust and scalable stock management operations.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Database Configuration](#database-configuration)
- [API Endpoints](#api-endpoints)
- [Entity Models](#entity-models)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Author](#author)
- [License](#license)

## Features

- ✅ Create new stocks
- ✅ Read/Retrieve stock information
- ✅ Update existing stock details
- ✅ Delete stocks from inventory
- ✅ Validation of stock data
- ✅ RESTful API architecture
- ✅ MySQL database integration
- ✅ Comprehensive error handling

## Technologies Used

| Technology | Version |
|------------|---------|
| Java | 21 |
| Spring Boot | 3.5.1 |
| Maven | 4.0 |
| MySQL | 8.0+ |
| Hibernate | 6.6+ |
| Lombok | 1.18.38 |

## Prerequisites

Before running this application, ensure you have the following installed:

- **Java 21** or higher
- **Maven 4.0** or higher
- **MySQL 8.0** or higher
- **Git** (optional, for version control)

## Project Structure

```
Stock-Management-Application/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/geekster/project/StockManagementApplication/
│   │   │   ├── StockManagementApplication.java (Main Application)
│   │   │   ├── Controller/
│   │   │   │   └── StockController.java
│   │   │   ├── Service/
│   │   │   │   └── StockService.java
│   │   │   ├── Repository/
│   │   │   │   └── IStockRepo.java
│   │   │   └── Model/
│   │   │       ├── Stock.java
│   │   │       └── StockType.java
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/com/example/geekster/project/StockManagementApplication/
│           └── StockManagementApplicationTests.java
│
├── pom.xml
├── README.md
└── LICENSE

```

## Dependencies

The following key dependencies are included in the project:

### Spring Boot Starters

- **spring-boot-starter-web**: Provides web application support including REST API capabilities
- **spring-boot-starter-data-jpa**: Simplifies database access using JPA and Spring Data repositories
- **spring-boot-starter-validation**: Includes validation support for request/response data binding
- **spring-boot-starter-test**: Testing framework support

### Database Driver

- **mysql-connector-j**: MySQL JDBC driver for database connectivity

### Other Libraries

- **Lombok**: Reduces boilerplate code by generating getters, setters, constructors, etc.

## Database Configuration

### MySQL Setup

1. **Create Database:**
```sql
CREATE DATABASE stock_management;
USE stock_management;
```

2. **Application Configuration** (`application.properties`):
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/stock_management
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## API Endpoints

### Base URL: `http://localhost:8080/api/stocks`

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Retrieve all stocks |
| GET | `/{id}` | Retrieve stock by ID |
| POST | `/` | Create a new stock |
| PUT | `/{id}` | Update a stock |
| DELETE | `/{id}` | Delete a stock |

### Example Request (Create Stock):
```json
POST /api/stocks
{
  "stockName": "Apple Inc.",
  "stockPrice": 150.50,
  "stockMarketCap": 2500000000,
  "stockType": "GROWTH",
  "stockBirthTimeStamp": "2024-01-20T10:00:00"
}
```

## Entity Models

### Stock Entity
```java
@Entity
@Table(name = "STOCK")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;
    
    private String stockName;
    private Double stockPrice;
    private Double stockMarketCap;
    
    @Enumerated(EnumType.STRING)
    private StockType stockType;
    
    private LocalDateTime stockBirthTimeStamp;
}
```

### StockType Enum
```java
public enum StockType {
    GROWTH,
    VALUE,
    DIVIDEND,
    TECH
}
```

## Installation & Setup

1. **Clone the Repository:**
```bash
git clone https://github.com/yourusername/Stock-Management-Application.git
cd Stock-Management-Application
```

2. **Configure MySQL Database:**
   - Ensure MySQL is running
   - Create the database as mentioned in the Database Configuration section

3. **Build the Project:**
```bash
mvn clean install
```

4. **Run the Application:**
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Usage

### Via REST Client (Postman, cURL, etc.)

1. **Create a Stock:**
```bash
curl -X POST http://localhost:8080/api/stocks \
  -H "Content-Type: application/json" \
  -d '{
    "stockName": "Tesla Inc.",
    "stockPrice": 250.75,
    "stockMarketCap": 800000000,
    "stockType": "GROWTH",
    "stockBirthTimeStamp": "2024-01-20T10:00:00"
  }'
```

2. **Retrieve All Stocks:**
```bash
curl http://localhost:8080/api/stocks
```

3. **Retrieve Stock by ID:**
```bash
curl http://localhost:8080/api/stocks/1
```

4. **Update a Stock:**
```bash
curl -X PUT http://localhost:8080/api/stocks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "stockName": "Tesla Inc.",
    "stockPrice": 275.00,
    "stockMarketCap": 850000000,
    "stockType": "GROWTH",
    "stockBirthTimeStamp": "2024-01-20T10:00:00"
  }'
```

5. **Delete a Stock:**
```bash
curl -X DELETE http://localhost:8080/api/stocks/1
```

## Author

**Atul Kumar Upadhyay**

## License

This project is licensed under the **BSD 3-Clause License** - see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  <strong>Happy Coding! 🚀</strong>
</p>


## Technologies Used

- **Framework:** Spring Boot
- **Language:** Java 21
- **Build Tool:** Apache Maven 4.0.0

## Dependencies

The Stock Management Application project relies on the following dependencies:

- **Spring Boot Starter Data JPA**
  - **Description:** Provides support for JPA (Java Persistence API) and simplifies database access using Spring Data repositories.
  - **Maven Dependency:**
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

- **Spring Boot Starter Validation**
  - **Description:** Includes validation support for request data binding and response data rendering.
  - **Maven Dependency:**
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    ```

- **Spring Boot Starter Web**
  - **Description:** Provides support for building web applications, including RESTful APIs.
  - **Maven Dependency:**
    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

- **H2 Database (Runtime Dependency)**
  - **Description:** An in-memory database for development and testing.
  - **Maven Dependency:**
    ```xml
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    ```

- **Project Lombok (Optional)**
  - **Description:** A library that simplifies Java code by reducing boilerplate code, such as getters and setters.
  - **Maven Dependency:**
    ```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    ```

- **Spring Boot Maven Plugin**
  - **Description:** A plugin for building and packaging Spring Boot applications.
  - **Maven Plugin Configuration (in your project's `pom.xml`):**
    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    ```

## Data Flow

### Stock Entity

- **Controller Layer**

  The `StockController` handles HTTP requests related to stocks and routes them to the `StockService`.

  ```java
  // StockController.java

  @RestController
  @RequestMapping("/stocks")
  public class StockController {
      // Define stock-related endpoints and methods
  }
  ```

- **Service Layer**

  The `StockService` contains business logic and interacts with the `StockRepository` to perform CRUD operations on stock data.

  ```java
  // StockService.java

  @Service
  public class StockService {
      // Implement stock-related service methods
  }
  ```

- **Repository Layer**

  The `StockRepository` manages data access to the stock entity using Spring Data JPA.

  ```java
  // StockRepository.java

  @Repository
  public interface StockRepository extends JpaRepository<Stock, Long> {
      // Define custom queries or repository methods if needed
  }
  ```

- **Stock Entity**

  The `Stock` entity represents the structure of stock data in the database.

  ```java
  // Stock.java (Stock Entity)

  @Entity
  public class Stock {
      // Define stock attributes, getters, setters, etc.
  }
  ```

  
## Architecture

The application follows the MVC (Model-View-Controller) architectural pattern. Key components include:

- **Controller:** Handles HTTP requests and routes them to appropriate services.
- **Service:** Contains business logic and interacts with the repository.
- **Model:** Represents the data structures (e.g., `Stock` entity and `StockType` enum).
- **Repository:** Performs database operations using Spring Data JPA.

## Endpoints

### Get Stocks by Type

- **HTTP Method:** GET
- **Endpoint:** `/stock/by-type/{stockType}`
- **Description:** Retrieves stocks by their type.
- **Example:** `/stock/by-type/ENERGY`

### Get Stocks Above Price and Lower Date

- **HTTP Method:** GET
- **Endpoint:** `/stock/abovePrice/price/{price}/lowerDate/date/{date}`
- **Description:** Retrieves stocks above a specific price and before a given date.
- **Example:** `/stock/abovePrice/price/100/lowerDate/date/2023-01-01T00:00:00`

### Get Stocks Above Market Cap

- **HTTP Method:** GET
- **Endpoint:** `/stock/cap/{capPercentage}`
- **Description:** Retrieves stocks with a market cap above a certain percentage.
- **Example:** `/stock/cap/10.0`

### Insert Stocks

- **HTTP Method:** POST
- **Endpoint:** `/stock/stocks`
- **Description:** Inserts a list of stocks into the database.
- **Request Body:** List of `Stock` objects.

### Update Market Cap

- **HTTP Method:** PUT
- **Endpoint:** `/stock/marketCap/{marketCap}/id/{id}`
- **Description:** Updates the market cap of a specific stock by ID.
- **Example:** `/stock/marketCap/500.0/id/1`

### Update Type by ID

- **HTTP Method:** PUT
- **Endpoint:** `/stock/stock/type/id`
- **Description:** Updates the stock type by ID.
- **Request Parameters:** `stockType` (String) and `id` (Integer).

### Update Stock by ID

- **HTTP Method:** PUT
- **Endpoint:** `/stock/stock/{id}`
- **Description:** Updates a stock by ID.
- **Request Body:** `Stock` object with updated data.

### Remove Stocks by Owner Count

- **HTTP Method:** DELETE
- **Endpoint:** `/stock/ownerCount/{count}`
- **Description:** Deletes stocks based on the owner count.
- **Example:** `/stock/ownerCount/5`

## Database Table

The application uses a MySQL database to store stock data. Below is the description of the `Stock` table:

| Column Name        | Data Type         | Description                                       |
| ------------------ | ----------------- | ------------------------------------------------- |
| stockId (Primary)  | INT (Auto Increment) | Unique identifier for each stock                |
| stockName (Unique) | VARCHAR(255)      | Name of the stock                                 |
| stockPrice         | DOUBLE            | Price of the stock                                |
| stockOwnerCount    | INT               | Owner count of the stock                          |
| stockType          | ENUM              | Type of the stock (enum values defined in code)  |
| stockMarketCap     | DOUBLE            | Market capitalization of the stock                |
| stockBirthTimeStamp| DATETIME          | Timestamp of when the stock was created           |


## Data Structures

### Stock Class

The `Stock` class defines the structure for stock data and includes fields such as `id`, `name`, `quantity`, and `price`.

```java
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer quantity;
    private BigDecimal price;

    // Define relationships, getters, setters, etc.
}
```

## Database Configuration

To configure the database, modify the `application.properties` file with your database connection details:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mappings
spring.datasource.username=root
spring.datasource.password=9892321787@As
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.use_sql_comments=true
spring.jpa.properties.hibernate.format_sql=true
```

Ensure that your MySQL database server is running and the credentials match your database setup.

## Usage

1. Start the Spring Boot application.
2. Access the provided API endpoints to perform CRUD operations on stocks.

## License

This project is licensed under the [BSD 3-Clause License](LICENSE).

## Contact

For questions or feedback, please contact [Amit Ashok Swain](mailto:business.amitswain@gmail.com).

---

