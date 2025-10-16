# 🪙 Gold Inventory Management System

The **Gold Inventory** project is a backend system built to manage gold hubs and handle customer gold transactions.  
It manages buying, selling, and tracking of gold bullion with automatic calculations for remaining balances, weights, and pricing.  
Admins can monitor all transactions and inventory levels in real time.

---

## 🚀 Features

- 🏛 **Hub Management:** Manage multiple gold hubs or branches.
- 💰 **Buy Gold Logic:** When a customer buys gold:
  - Example: Customer pays **10,600 EGP**.
  - Price per gram = **5,000 EGP**.
  - The system calculates:
    - 4 bullions × 5 grams = 20 grams (worth 20 × 5,000 = 100,000 EGP)
    - 1 bullion × 1 gram = 5,000 EGP
    - Total gold value = 105,000 EGP
    - Remaining balance returned = **1,000 EGP**.
- 🧾 **Admin Dashboard:** Admins can view, approve, or manage transactions.
- 📦 **Real-time Inventory:** Tracks gold weight and bullion availability in hubs.
- 🧠 **Automatic Calculation:** System automatically computes gold weights, counts, and remaining cash.
- 🔐 **Authentication:** Admin and user roles (Spring Security with JWT).
- 🧰 **Modern API Design:** RESTful endpoints for all operations.

---

## 🧑‍💼 Admin Dashboard Login

**Login URL:** http://localhost:8080/admin/login  
user_name : mostafa_sadoon
password : 123456

## ⚙️ Tech Stack

| Technology | Purpose |
|-------------|----------|
| **Spring Boot 3** | Core backend framework |
| **Spring Security + JWT** | Authentication and authorization |
| **Spring Data JPA / Hibernate** | ORM and database interaction |
| **MySQL / PostgreSQL** | Database |
| **MapStruct** | DTO ↔ Entity mapping |
| **Maven** | Build and dependency management |
| **Lombok** | Simplify model and DTO code |

---

## 🧱 Project Structure
```text
src
 └── main
     ├── java
     │   └── com.dahabMasr.GoldInventory
     │       ├── config
     │       │   └── SecurityConfig.java         # Configures multi-auth (Admin + Customer)
     │       │
     │       ├── controller
     │       │   ├── api                         # REST APIs (JWT-secured for customers)
     │       │   └── web                         # Web controllers (form login for admins)
     │       │
     │       ├── database.seeder                 # Seeds initial admin, customer, inventory data
     │       │   ├── AdminSeeder.java
     │       │   ├── CustomerSeeder.java
     │       │   └── InventorySeeder.java
     │       │
     │       ├── exception                       # Centralized error handling
     │       │   ├── GlobalExceptionHandler.java
     │       │   ├── InventoryNotFoundException.java
     │       │   └── TransactionNotFoundException.java
     │       │
     │       ├── model
     │       │   ├── Dto
     │       │   ├── Entity
     │       │   ├── enums.inventory
     │       │   └── Mapper
     │       │
     │       ├── repository
     │       ├── security                        # Custom JWT + UserDetails logic
     │       ├── service                         # Business logic layer
     │       ├── utility                         # Helper and utility classes
     │       └── GoldInventoryApplication.java   # Main Spring Boot entry point
     │
     └── resources
         ├── application.properties              # Database & JWT configurations
         └── templates (if using Thymeleaf)      # Admin login pages


