# EasyShop E-Commerce API

A Spring Boot REST API for an e-commerce web application built with Java, MySQL, and JWT authentication.

## Description

EasyShop is a full-stack e-commerce platform where users can browse products by category, filter by price and color, add items to a shopping cart, and checkout to place an order. The backend was built using Spring Boot with JPA/Hibernate for database interaction and JWT for secure authentication.

## Technologies Used

- Java 17
- Spring Boot
- Spring Security (JWT)
- JPA / Hibernate
- MySQL
- Insomnia (API Testing)

## Application Screenshots

### Home Page
<img width="1562" height="897" alt="image" src="https://github.com/user-attachments/assets/8e80a29b-94d3-489f-a25d-55e6969c3bcb" />

### Shopping Cart
<img width="1552" height="552" alt="image" src="https://github.com/user-attachments/assets/46d06b0b-7b72-483f-8bcc-d4b41af70382" />

## Interesting Code - Bug Fix in ProductService

Before my fix, this line was hiding most products from search results:
```java
.filter(Product::isFeatured)
```
Removing it allowed all products to return correctly when filtering by category, price, or color.








