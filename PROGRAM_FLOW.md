# 🔄 Stock Management Application - Program Flow

**Version:** 0.0.1-SNAPSHOT | **Java:** 21 | **Spring Boot:** 3.5.1 | **MySQL:** 8.0+

---

## 📊 High-Level Application Flow

```
┌─────────────────────────────────────────────────────────────────┐
│                    SPRING BOOT STARTUP                           │
│                 (Application Initialization)                     │
└────────────────────┬────────────────────────────────────────────┘
                     │
                     ▼
         ┌───────────────────────────┐
         │  1. JVM Starts Java 21     │
         │  2. Load Properties Files  │
         │  3. Initialize Spring      │
         └────────────┬────────────────┘
                     │
        ┌────────────▼────────────┐
        │ Load MySQL Connection   │
        │ (Root: Atul@123456)    │
        │ Host: localhost:3306    │
        │ DB: stock_management    │
        └────────────┬────────────┘
                     │
        ┌────────────▼────────────┐
        │ Hibernate Schema Check  │
        │ Create Table: stocks    │
        │ Add Constraints         │
        └────────────┬────────────┘
                     │
        ┌────────────▼────────────────┐
        │ Initialize JPA Repository   │
        │ Scan @Repository classes   │
        │ Create Spring Beans         │
        └────────────┬────────────────┘
                     │
        ┌────────────▼────────────────┐
        │ Start Tomcat Server         │
        │ Port: 8080                  │
        │ Context Path: /             │
        └────────────┬────────────────┘
                     │
        ┌────────────▼────────────────┐
        │ Application Ready!          │
        │ Listening for requests      │
        │ Status: RUNNING             │
        └────────────┬────────────────┘
```

---

## 🔀 Request Processing Flow

```
CLIENT (Browser / Postman / curl)
         │
         ▼
    ┌─────────────────────┐
    │ HTTP REQUEST        │
    │ (GET/POST/PUT/etc)  │
    │ /stocks/...         │
    └────────┬────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ SPRING DISPATCHER SERVLET           │
    │ Routes request to handler           │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ REQUEST MAPPING HANDLER             │
    │ @RequestMapping("/stocks")          │
    │ Routes to StockController           │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ STOCK CONTROLLER                    │
    │ ├─ getStocksByType()               │
    │ ├─ getStocksAbovePriceAndLowerDate()│
    │ ├─ addStocks()                      │
    │ ├─ updateMarketCap()                │
    │ ├─ updateStockById()                │
    │ └─ deleteStocks()                   │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ STOCK SERVICE                       │
    │ ├─ Call repository methods         │
    │ ├─ Apply business logic            │
    │ ├─ Handle transactions             │
    │ └─ Return results                  │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ JPA REPOSITORY (IStockRepo)        │
    │ ├─ findByStockType()               │
    │ ├─ findByStockPrice...()           │
    │ ├─ saveAll()                       │
    │ ├─ updateMarketCapById()           │
    │ └─ Custom @Query methods           │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ HIBERNATE ORM                       │
    │ ├─ Map entities to SQL             │
    │ ├─ Build SQL queries               │
    │ └─ Handle transactions             │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ HIKARI CONNECTION POOL             │
    │ ├─ Get database connection         │
    │ └─ Connection pooling (10 max)     │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ MYSQL DATABASE                      │
    │ ├─ Execute SQL query               │
    │ ├─ Lock rows if needed             │
    │ ├─ Commit transaction              │
    │ └─ Return result set               │
    └────────┬────────────────────────────┘
             │
             ▼ (Response travels back)
    ┌─────────────────────────────────────┐
    │ RESULT MAPPING                      │
    │ ├─ Map ResultSet to Stock objects  │
    │ └─ Convert to Java objects         │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ SERVICE LAYER                       │
    │ ├─ Process results                 │
    │ └─ Return to controller            │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ CONTROLLER LAYER                    │
    │ ├─ Wrap in ResponseEntity          │
    │ ├─ Set HTTP status (200/201/etc)  │
    │ └─ Add response headers            │
    └────────┬────────────────────────────┘
             │
             ▼
    ┌─────────────────────────────────────┐
    │ HTTP RESPONSE                       │
    │ ├─ Status Code                     │
    │ ├─ Headers                         │
    │ └─ Body (JSON)                     │
    └────────┬────────────────────────────┘
             │
             ▼
    CLIENT (Receives Response)
```

---

## 🎯 Endpoint-Specific Flows

### 1️⃣ GET /stocks/type/{stockType} - Get Stocks by Type

```
Request: GET /stocks/type/AUTOMOBILE
         │
         ▼
Controller.getStocksByType(StockType.AUTOMOBILE)
         │
         ▼
Service.getStocksByType(StockType)
         │
         ▼
Repository.findByStockType(StockType) ◀─── JPA Derived Query
         │
         ▼
Hibernate generates:
SELECT * FROM stocks WHERE stock_type = 'AUTOMOBILE'
         │
         ▼
Database executes query
         │
         ▼
Results: List<Stock>
         │
         ▼
Response: HTTP 200 OK
         [
           { "stockId": 1, "stockName": "Tesla", "stockType": "AUTOMOBILE", ... },
           { "stockId": 2, "stockName": "Ford", "stockType": "AUTOMOBILE", ... }
         ]
```

### 2️⃣ POST /stocks - Add New Stocks

```
Request: POST /stocks
         Content-Type: application/json
         Body: [
           { "stockName": "Apple", "stockPrice": 150.5, ... },
           { "stockName": "Tesla", "stockPrice": 900.0, ... }
         ]
         │
         ▼
@RequestBody List<Stock> deserialization
         │
         ▼
Controller.addStocks(List<Stock>)
         │
         ▼
Service.addStocks(List)
         │
         ▼
@Transactional
Repository.saveAll(List) ◀─── Bulk insert operation
         │
         ▼
Hibernate generates:
INSERT INTO stocks (stock_name, stock_price, stock_type, ...)
VALUES ('Apple', 150.5, 'INFORMATION_TECHNOLOGY', ...),
       ('Tesla', 900.0, 'AUTOMOBILE', ...)
         │
         ▼
Database executes (auto-increment ID generated)
         │
         ▼
@Transactional commits
         │
         ▼
Response: HTTP 200 OK
         "Stocks added successfully"
```

### 3️⃣ PUT /stocks/{id}/market-cap - Update Market Cap

```
Request: PUT /stocks/1/market-cap?marketCap=3000
         │
         ▼
Controller.updateMarketCap(id=1, marketCap=3000)
         │
         ▼
Service.updateMarketCap(3000, 1)
         │
         ▼
@Transactional
@Modifying
Repository.updateMarketCapById(@Param("cap") 3000, @Param("id") 1)
         │
         ▼
Hibernate generates:
UPDATE stocks SET stock_market_cap = 3000 WHERE stock_id = 1
         │
         ▼
Database executes (1 row updated)
         │
         ▼
@Transactional commits
         │
         ▼
Response: HTTP 204 No Content
```

### 4️⃣ GET /stocks/filter - Advanced Filtering

```
Request: GET /stocks/filter?price=100&date=2026-12-31T23:59:59
         │
         ▼
RequestParam price=100, date=2026-12-31T23:59:59
         │
         ▼
Controller.getStocksAbovePriceAndLowerDate(100, "2026-12-31T23:59:59")
         │
         ▼
Service.getStocksAbovePriceAndLowerDate(100, date)
         │
         ├─ Parse LocalDateTime from string
         └─ Call repository
         │
         ▼
Repository.findByStockPriceGreaterThanAndStockBirthTimeStampLessThanOrderByStockName(
    100, LocalDateTime.parse("2026-12-31T23:59:59")
)
         │
         ▼
Hibernate generates:
SELECT * FROM stocks 
WHERE stock_price > 100 
AND stock_birth_time_stamp < '2026-12-31 23:59:59'
ORDER BY stock_name ASC
         │
         ▼
Database executes
         │
         ▼
Response: HTTP 200 OK
         [
           { "stockId": 1, "stockName": "Apple", "stockPrice": 150.5, ... },
           { "stockId": 3, "stockName": "Microsoft", "stockPrice": 320.0, ... }
         ]
```

---

## 🗄️ Entity Flow - Stock Object Lifecycle

```
┌──────────────────────────────────┐
│ Stock Entity Definition          │
│ ├─ stockId (ID, auto-increment)  │
│ ├─ stockName (unique, required)  │
│ ├─ stockPrice (double)           │
│ ├─ stockOwnerCount (integer)     │
│ ├─ stockType (enum - 17 types)   │
│ ├─ stockMarketCap (double)       │
│ └─ stockBirthTimeStamp (datetime)│
└──────────────────────────────────┘
         │
         ▼
   ┌─────────────────┐
   │ CREATE          │
   │ (New Stock)     │
   └────────┬────────┘
            │
         ┌──┴───┐
         │      │
         ▼      ▼
    POST    Hibernate
   /stocks  Maps to
    Request SQL INSERT
         │      │
         ▼      ▼
       ┌─────────────┐
       │   PERSIST   │  ◀── State: Managed Entity
       │   (Saved)   │
       └──────┬──────┘
              │
         ┌────┴─────┐
         │           │
         ▼           ▼
    SELECT      UPDATE
     Query      Request
         │           │
         ▼           ▼
    ┌─────────────┐  ┌──────────────┐
    │ READ (DB)   │  │ UPDATE (DB)  │
    │ Maps to     │  │ Maps to      │
    │ Java Object │  │ SQL UPDATE   │
    └────┬────────┘  └──────┬───────┘
         │                  │
         ▼                  ▼
    ┌─────────────────────────────┐
    │ IN-MEMORY JAVA OBJECT       │
    │ Fully loaded entity         │
    │ State: Persistent/Managed   │
    └────────┬────────────────────┘
             │
         ┌───┴────┐
         │        │
         ▼        ▼
     DELETE    Modify &
     Request   Save
         │        │
         ▼        ▼
    ┌──────────────────────────────┐
    │ @Transactional ends          │
    │ Hibernate flushes changes    │
    │ Database commits            │
    └──────────────────────────────┘
             │
             ▼
    ┌──────────────────────────────┐
    │ DETACHED (Session closed)    │
    │ Object still in memory       │
    │ Changes won't auto-sync      │
    └──────────────────────────────┘
```

---

## 💾 Database Transaction Flow

```
┌─────────────────────────────────────────────────────────┐
│ @Transactional Method Begins                           │
│ (IsolationLevel.DEFAULT, Propagation.REQUIRED)         │
└────────────┬────────────────────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────────────────────┐
│ HikariCP: Get Connection from Pool (max 10)           │
│ Or create new if available                            │
└────────────┬────────────────────────────────────────────┘
             │
             ▼
┌─────────────────────────────────────────────────────────┐
│ MySQL: BEGIN TRANSACTION (InnoDB)                      │
│ Transaction Level: READ_COMMITTED (default)            │
└────────────┬────────────────────────────────────────────┘
             │
      ┌──────┴───────┐
      │              │
      ▼              ▼
  ┌─────────────┐ ┌──────────────┐
  │ READ        │ │ WRITE        │
  │ SELECT      │ │ INSERT       │
  │ (No locks)  │ │ UPDATE       │
  │             │ │ DELETE       │
  └──────┬──────┘ │ (Locks rows) │
         │        └──────┬───────┘
         │               │
         └───────┬───────┘
                 │
                 ▼
    ┌──────────────────────────┐
    │ Method execution         │
    │ (Business logic)         │
    │ (Data processing)        │
    └────────┬─────────────────┘
             │
         ┌───┴────────────────────┐
         │                        │
         ▼                        ▼
    SUCCESS                  EXCEPTION
         │                        │
         ▼                        ▼
    ┌──────────────┐         ┌─────────────────┐
    │ COMMIT       │         │ ROLLBACK        │
    │ Changes      │         │ Discard all     │
    │ persisted    │         │ changes         │
    │ to DB        │         │                 │
    └──────┬───────┘         └────────┬────────┘
           │                         │
           └────────┬────────────────┘
                    │
                    ▼
          ┌─────────────────────┐
          │ Connection returned │
          │ to HikariCP pool    │
          │ (Ready to reuse)    │
          └─────────────────────┘
```

---

## 🔀 Error Handling Flow

```
┌──────────────────────────┐
│ Request Arrives          │
│ with Invalid Data        │
└────────┬─────────────────┘
         │
         ▼
┌─────────────────────────────────────────┐
│ Spring Validation Interceptor           │
│ @Valid @RequestBody checks              │
│ ├─ @NotNull                            │
│ ├─ @NotBlank                           │
│ └─ Custom validators                   │
└────────┬────────────────────────────────┘
         │
    ┌────┴─────────┐
    │              │
    ▼              ▼
 VALID          INVALID
    │              │
    │              ▼
    │        ┌──────────────────┐
    │        │ ValidationError  │
    │        │ Exception thrown │
    │        └────────┬─────────┘
    │               │
    │               ▼
    │        ┌───────────────────────┐
    │        │ @ExceptionHandler    │
    │        │ or Global handler    │
    │        └────────┬──────────────┘
    │               │
    │               ▼
    │        ┌───────────────────────┐
    │        │ Return HTTP 400       │
    │        │ Bad Request           │
    │        │ Error message in body │
    │        └───────────────────────┘
    │
    ▼
Service execution
    │
    ├─ Database connection error ───────┐
    │                                   │
    ├─ SQL constraint violation ────────┤
    │                                   │
    └─ Transaction rollback ────────────┤
                                        │
                                        ▼
                            ┌─────────────────────────┐
                            │ Exception logged        │
                            │ Rolled back changes     │
                            │ Return HTTP 500/400     │
                            │ Error response to client│
                            └─────────────────────────┘
```

---

## 🔐 Security Flow

```
┌──────────────────────────────────┐
│ Incoming HTTP Request            │
└────────┬─────────────────────────┘
         │
         ▼
┌────────────────────────────────────────┐
│ Spring Security Filter Chain           │
│ (Even if not explicitly configured)   │
└────────┬─────────────────────────────────┘
         │
         ▼
┌────────────────────────────────────────┐
│ Validation Layer                       │
│ ├─ Check @Valid annotations          │
│ ├─ Validate field constraints         │
│ └─ Prevent invalid data flow          │
└────────┬─────────────────────────────────┘
         │
         ▼
┌────────────────────────────────────────┐
│ Data Access Layer Security            │
│ ├─ Parameterized queries (prevent SQL injection)    │
│ ├─ JPA @Query with @Param             │
│ ├─ Spring Data derived queries        │
│ └─ Never concatenate strings          │
└────────┬─────────────────────────────────┘
         │
         ▼
┌────────────────────────────────────────┐
│ Database Connection Security           │
│ ├─ Username/Password (encrypted in props)   │
│ ├─ HikariCP managed pool              │
│ ├─ SSL connection option available    │
│ └─ Transaction isolation level        │
└────────┬─────────────────────────────────┘
         │
         ▼
┌────────────────────────────────────────┐
│ Response Serialization Security        │
│ ├─ JSON serialization via Jackson      │
│ ├─ No sensitive data exposure         │
│ ├─ Proper HTTP headers set            │
│ └─ No stack traces in production      │
└────────────────────────────────────────┘
```

---

## 📈 Performance Flow

```
Request arrives at port 8080
         │
         ▼
┌──────────────────────────┐
│ Thread pool (50 default) │
│ Assign worker thread     │
└────────┬─────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ Spring DispatcherServlet     │
│ Fast routing (~1ms)          │
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ Spring MVC handler mapping   │
│ Match @RequestMapping        │
│ (~1ms)                       │
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ Controller method invoked    │
│ Business logic (~10-50ms)    │
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ Service layer processing     │
│ Validation, transformation   │
│ (~5-20ms)                    │
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ HikariCP get connection      │
│ From pool (~1ms)             │
│ Or wait if all in use        │
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ Hibernate query execution    │
│ Build SQL, execute           │
│ (~20-100ms depending on query)
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ MySQL database processing    │
│ Query execution, locking     │
│ (~50-500ms depending on data)│
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ Result set mapping           │
│ Convert to Java objects      │
│ (~10-50ms)                   │
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ JSON serialization           │
│ Convert to JSON string       │
│ (~5-20ms)                    │
└────────┬─────────────────────┘
         │
         ▼
┌──────────────────────────────┐
│ Send HTTP response           │
│ Write to socket (~1-5ms)     │
└────────┬─────────────────────┘
         │
         ▼
Total: 100-700ms (typical for complex query)
       50-200ms (for simple operations)
```

---

## 🎓 Class Interaction Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                  StockManagementApplication                  │
│                     @SpringBootApplication                   │
│                   main(String[] args) {                      │
│                  SpringApplication.run(...)                  │
│                   }                                          │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
        ┌──────────────────────────────────────┐
        │     StockController                  │
        │     @RestController                  │
        │     @RequestMapping("/stocks")       │
        ├──────────────────────────────────────┤
        │ ├─ getStocksByType()                │
        │ ├─ getStocksAbovePriceAndLowerDate()│
        │ ├─ addStocks()                      │
        │ ├─ updateMarketCap()                │
        │ ├─ updateStockById()                │
        │ └─ deleteStocks()                   │
        │                                      │
        │ @Autowired StockService             │
        └──────────┬───────────────────────────┘
                   │ delegates to
                   ▼
        ┌──────────────────────────────────────┐
        │     StockService                     │
        │     @Service                         │
        │     @Transactional                   │
        ├──────────────────────────────────────┤
        │ ├─ getStocksByType()                │
        │ ├─ addStocks()                      │
        │ ├─ getStocksAbovePriceAndLowerDate()│
        │ ├─ updateMarketCap()                │
        │ ├─ deleteStocksBasedOnCount()       │
        │ └─ updateStockById()                │
        │                                      │
        │ @Autowired IStockRepo               │
        └──────────┬───────────────────────────┘
                   │ delegates to
                   ▼
        ┌──────────────────────────────────────┐
        │     IStockRepo                       │
        │     @Repository                      │
        │     extends JpaRepository<Stock>     │
        ├──────────────────────────────────────┤
        │ ├─ findByStockType()                │
        │ ├─ findByStockPrice...()            │
        │ ├─ updateMarketCapById()            │
        │ └─ Custom @Query methods            │
        │                                      │
        │ Managed by: Spring Data JPA          │
        │ Uses: Hibernate ORM                  │
        └──────────┬───────────────────────────┘
                   │ maps to
                   ▼
        ┌──────────────────────────────────────┐
        │     Stock Entity                     │
        │     @Entity                          │
        │     @Table(name = "stocks")          │
        ├──────────────────────────────────────┤
        │ ├─ @Id stockId                      │
        │ ├─ stockName                        │
        │ ├─ stockPrice                       │
        │ ├─ stockOwnerCount                  │
        │ ├─ @Enumerated stockType            │
        │ ├─ stockMarketCap                   │
        │ └─ stockBirthTimeStamp              │
        │                                      │
        │ Annotations: Lombok @Data, @AllArgsConstructor
        └──────────┬───────────────────────────┘
                   │ uses
                   ▼
        ┌──────────────────────────────────────┐
        │     StockType Enum                   │
        │     enum StockType {                 │
        ├──────────────────────────────────────┤
        │ - AUTOMOBILE                        │
        │ - COMMUNICATION_SERVICES            │
        │ - CONSUMER_DISCRETIONARY            │
        │ - CONSUMER_STAPLES                  │
        │ - CRYPTO_CURRENCY                   │
        │ - DELIVERY                          │
        │ - ENERGY                            │
        │ - ENTERTAINMENT                     │
        │ - FINANCIALS                        │
        │ - HEALTHCARE                        │
        │ - INDUSTRIALS                       │
        │ - INFORMATION_TECHNOLOGY            │
        │ - INFRASTRUCTURE                    │
        │ - MATERIALS                         │
        │ - MINING                            │
        │ - REAL_ESTATE                       │
        │ - TRANSPORTATION                    │
        │ - UTILITIES                         │
        │ }                                    │
        └──────────────────────────────────────┘
```

---

## 📱 Dependency Injection Flow

```
Spring Context Initialization
         │
         ▼
┌─────────────────────────────────┐
│ Scan @SpringBootApplication     │
│ basePackages:                   │
│ com.example.geekster.project... │
└────────┬────────────────────────┘
         │
         ▼
┌─────────────────────────────────┐
│ Find Component Classes:         │
│ 1. @Repository StockRepo        │
│ 2. @Service StockService        │
│ 3. @RestController              │
│ 4. @Entity Stock                │
└────────┬────────────────────────┘
         │
         ▼
┌─────────────────────────────────┐
│ Create Bean Instances:          │
│ • IStockRepo bean               │
│ • StockService bean             │
│ • StockController bean          │
└────────┬────────────────────────┘
         │
         ▼
┌─────────────────────────────────┐
│ Dependency Resolution:          │
│ @Autowired StockService in      │
│ StockController (inject)        │
│                                 │
│ @Autowired IStockRepo in        │
│ StockService (inject)           │
└────────┬────────────────────────┘
         │
         ▼
┌─────────────────────────────────┐
│ Application Context Ready       │
│ All beans in singleton scope    │
│ (created once, reused)          │
└─────────────────────────────────┘
         │
         ▼
Now ready to serve requests!
```

---

## 🔍 Query Execution Example

```
Request: GET /stocks/type/AUTOMOBILE

StockController.getStocksByType(StockType.AUTOMOBILE)
         │
         ▼
StockService.getStocksByType(StockType.AUTOMOBILE)
         │
         ▼
IStockRepo.findByStockType(StockType.AUTOMOBILE)
         │
         ▼
Spring Data JPA Generated Query Method
         │
    ┌────┴────────────────────────────────────────────┐
    │                                                  │
    ▼                                                  ▼
Property names:                              Method signature parsing:
- StockType → stock_type                     findByStockType
- Stock → implicit alias 's'                 • Find = SELECT
                                             • By = WHERE
                                             • StockType = stock_type
                                             • Parameter = :stockType (positional)
         │                                            │
         ▼                                            ▼
         ┌──────────────────────────────────────────┐
         │ Generated Hibernate Query:                │
         │ from Stock s where s.stockType = ?1     │
         └──────────┬───────────────────────────────┘
                    │
                    ▼
         ┌──────────────────────────────────────────┐
         │ Converted to SQL:                         │
         │ SELECT * FROM stocks                     │
         │ WHERE stock_type = 'AUTOMOBILE'          │
         └──────────┬───────────────────────────────┘
                    │
                    ▼
         ┌──────────────────────────────────────────┐
         │ MySQL Execution:                          │
         │ • Find all rows where stock_type=AUTO   │
         │ • Return result set                     │
         │ • Example: [Tesla, Ford, BMW]           │
         └──────────┬───────────────────────────────┘
                    │
                    ▼
         ┌──────────────────────────────────────────┐
         │ Hibernate Result Mapping:                 │
         │ ResultSet → List<Stock> objects         │
         │ Fields mapped from SQL columns to Java  │
         └──────────┬───────────────────────────────┘
                    │
                    ▼
Service returns List<Stock>
         │
         ▼
Controller wraps in ResponseEntity<List<Stock>>
         │
         ▼
Jackson serializes to JSON:
[
  { "stockId": 1, "stockName": "Tesla", "stockType": "AUTOMOBILE", ... },
  { "stockId": 2, "stockName": "Ford", "stockType": "AUTOMOBILE", ... }
]
         │
         ▼
HTTP 200 OK response sent to client
```

---

## 🚀 Complete Application Lifecycle

```
START (java -jar application.jar)
  │
  ▼
JVM loads application
  │
  ▼
Spring Boot main() called
  ▼
SpringApplication.run(Application.class)
  ├─ Load application.properties
  ├─ Initialize Spring context
  ├─ Load configuration classes
  ├─ Create bean definitions
  ├─ Instantiate beans
  ├─ Wire dependencies (@Autowired)
  └─ Post-construct methods
  │
  ▼
Spring Data JPA Repositories
  ├─ Scan @Repository classes
  ├─ Create proxy implementations
  └─ Register as managed beans
  │
  ▼
Hibernate Initialization
  ├─ Read entity mappings
  ├─ Connect to MySQL
  ├─ Initialize session factory
  └─ Create schema if needed
  │
  ▼
Database Connection Setup
  ├─ HikariCP initialize
  ├─ Create connection pool
  ├─ Test MySQL connection
  └─ Ready for queries
  │
  ▼
Servlet Container (Tomcat)
  ├─ Initialize Tomcat server
  ├─ Register DispatcherServlet
  ├─ Setup filter chains
  └─ Listen on port 8080
  │
  ▼
Application RUNNING
  (Ready to accept requests)
  │
  ├─ Continuous: Handle requests
  ├─ Continuous: Execute business logic
  ├─ Continuous: Query database
  ├─ Continuous: Return responses
  │
  ▼
Shutdown Signal (Ctrl+C)
  │
  ▼
Graceful Shutdown
  ├─ Stop accepting new requests
  ├─ Wait for active requests to finish
  ├─ Close database connections
  ├─ Shutdown Tomcat
  └─ Close Spring context
  │
  ▼
Application STOPPED
```

---

## 📊 Data Flow in Stock Creation Example

```
User sends JSON POST:
{
  "stockName": "Apple",
  "stockPrice": 150.50,
  "stockOwnerCount": 2000,
  "stockType": "INFORMATION_TECHNOLOGY",
  "stockMarketCap": 2500.75,
  "stockBirthTimeStamp": "2026-01-20T09:30:00"
}
         │
         ▼
Spring receives HTTP POST /stocks
         │
         ▼
Jackson deserializes JSON → Stock object
         │
         ▼
Spring Validation checks @Valid
         │
         ├─ Check @NotNull fields
         ├─ Check @NotBlank values
         └─ All pass ✓
         │
         ▼
StockController.addStocks(List<Stock>)
         │
         ▼
StockService.addStocks(List<Stock>)
         │
         ▼
@Transactional begins
         │
         ▼
IStockRepo.saveAll(List<Stock>)
         │
         ▼
Hibernate: For each Stock object
  ├─ Generate INSERT SQL
  ├─ Set parameters (@Param)
  └─ Prepare statement
         │
         ▼
HikariCP: Get database connection from pool
         │
         ▼
MySQL: Execute INSERT statement:
INSERT INTO stocks (stock_name, stock_price, stock_owner_count,
                   stock_type, stock_market_cap, stock_birth_time_stamp)
VALUES ('Apple', 150.50, 2000, 'INFORMATION_TECHNOLOGY', 2500.75,
        '2026-01-20 09:30:00')
         │
         ▼
MySQL: Auto-increment generates stockId = 1
         │
         ▼
MySQL: Row inserted ✓
         │
         ▼
Hibernate: Map result back
  ├─ Retrieve generated ID
  ├─ Update Stock object with stockId=1
  └─ Return from repository
         │
         ▼
Service receives List with updated Stock objects
         │
         ▼
Return "Stocks added successfully" message
         │
         ▼
@Transactional: COMMIT executed
         │
         ▼
Database connection returned to HikariCP pool
         │
         ▼
Controller: Wrap in ResponseEntity<String>
         │
         ▼
Spring: Convert to JSON response:
{
  "message": "Stocks added successfully"
}
         │
         ▼
Return HTTP 200 OK with body
         │
         ▼
Client receives response!
```

---

**🎉 Program flow complete and ready for production deployment!**

*Document Version: 1.0 | Last Updated: January 20, 2026 | Author: Atul Kumar Upadhyay*
