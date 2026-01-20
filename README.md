# Stock Management Application
A **Spring Boot-based CRUD application** for managing stocks. Add, update, and retrieve stock data with efficient RESTful APIs.

## Author
**Atul Kumar Upadhyay**  
📧 Email: atulupa12345@gmail.com

## Features
- Built with **Java 21, Spring Boot 3.x, Spring Data JPA, MySQL**
- REST APIs to:
  - Get stocks by type
  - Get stocks above a specific price before a date
  - Add new stocks
  - Update market capitalization
  - Update full stock details
  - Delete stocks by owner count
- Scalable MVC architecture
- Fully transactional operations
- Clean JPA queries

## Architecture
| Layer       | File(s)                         | Description                   |
|------------|---------------------------------|-------------------------------|
| Controller | `StockController.java`          | Handles HTTP requests         |
| Service    | `StockService.java`             | Business logic               |
| Repository | `IStockRepo.java`               | JPA repository + custom queries |
| Model      | `Stock.java`, `StockType.java`  | Entity & enum definitions    |

## Endpoints
| HTTP Method | URL | Description |
|-------------|-----|------------|
| GET | `/stocks/type/{stockType}` | Get all stocks of a specific type |
| GET | `/stocks/filter?price={price}&date={yyyy-MM-ddTHH:mm:ss}` | Get stocks above a price and before a date |
| POST | `/stocks` | Add new stocks (send JSON array) |
| PUT | `/stocks/{id}/market-cap?marketCap={value}` | Update market capitalization |
| PUT | `/stocks/{id}` | Update full stock by ID (send stock JSON) |
| DELETE | `/stocks/owner-count/{count}` | Delete stocks with owner count ≤ count |

## Database Configuration
### Production DB (MySQL)
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/stock_management
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.show-sql=true
```

### Test DB
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/stock_management_test
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
```

## Example JSON for POST /stocks
```json
[
  {
    "stockName": "Apple",
    "stockPrice": 150.5,
    "stockOwnerCount": 2000,
    "stockType": "INFORMATION_TECHNOLOGY",
    "stockMarketCap": 2500.75,
    "stockBirthTimeStamp": "2026-01-20T09:30:00"
  },
  {
    "stockName": "Tesla",
    "stockPrice": 900.0,
    "stockOwnerCount": 500,
    "stockType": "AUTOMOBILE",
    "stockMarketCap": 900.25,
    "stockBirthTimeStamp": "2026-01-18T10:00:00"
  }
]
```

## Tech Stack
- Java 21
- Spring Boot 3.x
- Spring Data JPA
- MySQL 8
- Lombok
- Maven

## How to Run
1. Clone the repository
2. Update MySQL username/password in `application.properties`
3. Build project:
```bash
mvn clean install
```
4. Run Spring Boot:
```bash
mvn spring-boot:run
```
5. Access APIs at: `http://localhost:8080/stocks`

## Notes
- Fully tested with MySQL
- Transactional updates for safety
- GitHub contribution safe