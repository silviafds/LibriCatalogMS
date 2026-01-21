# 📚 LibriCatalogMS

## 📌 Book Catalog Microservice

LibriCatalogMS is a microservice responsible for managing the **book catalog** in the LibriBookshelf system.  
It provides CRUD operations for book records, supports partial updates, and integrates with other services via messaging. :contentReference[oaicite:1]{index=1}

## 🧠 Overview

This service handles:

- Book registration and persistence
- Retrieving book details by ID or title
- Listing all books
- Partial updates of book data
- Deleting books
- Responding to book title lookup requests from other services (e.g., Review Service) via RabbitMQ messaging

It follows a **hexagonal architecture**, separating the domain, mapping, persistence, and messaging layers. :contentReference[oaicite:2]{index=2}

## 🏛️ Architecture

                  +------------------+
                  |  API / Controller |
                  +------------------+
                               |
                 +-----------------------------+
                 |     Catalog Service Core     |
                 +-----------------------------+
                 | - Business Logic             |
                 | - Validations                |
                 | - DTO Mapping                |
                 +-----------------------------+
                               |
            +----------------------------------------------+
            | Persistence / Messaging / External Interfaces |
            +----------------------------------------------+
            | - PostgreSQL (via JPA)                        |
            | - RabbitMQ Catalog Queue                      |
            +----------------------------------------------+
            |
            +-----------------------------+
            |       External Systems       |
            +-----------------------------+
            | - Review Service (RPC MSG)   |
            +-----------------------------+



---

## 🚀 Features

### 📍 Book Management
- Create a new book with validation rules
- Retrieve all books
- Retrieve a specific book by ID
- Search a book by title
- Partial update of book fields
- Delete a book

### 🔗 Inter-Service Communication
- Responds to book title lookup requests over **RabbitMQ**
- Messaging follows a lightweight request–reply pattern

---

## 🛠️ Technologies Used

| Layer              | Technology                         |
|-------------------|------------------------------------|
| Framework         | Spring Boot                        |
| Persistence       | Spring Data JPA + PostgreSQL       |
| Messaging         | RabbitMQ                           |
| DTO Mapping       | Custom Mapper (e.g., MapStruct)    |
| Transaction       | Spring @Transactional              |
| Validation        | Custom service validation rules    |
| Logging           | SLF4J / Spring Logging             |
| Build             | Maven                              |

---

## 🧾 Getting Started

### Prerequisites

Ensure you have:

- Java 17+
- Maven
- PostgreSQL running and accessible
- RabbitMQ Broker
- Eureka (optional, if using service discovery)

---

## 🛡️ Environment Configuration

Create an `application.yml` or environment variables:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/libri-catalog
    username: user
    password: 123

  rabbitmq:
    host: localhost
    port: 5672
    username: bookshelf
    password: senha123

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
