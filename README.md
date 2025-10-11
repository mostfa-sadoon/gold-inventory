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

