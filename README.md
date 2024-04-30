# HTTP API for Customer Management

This project implements a basic CRUD (Create, Read, Update, Delete) API for managing customer entities, powered by Java 17.0.10 Coretto and Spring Boot 3.2.2, with a PostgreSQL database.

## Endpoints

### Create Customer
- **Method:** `POST`
- **URL:** `/api/customers`
- **Content-Type:** `application/json`
- **Body:**
  ```json
  {
    "fullName": "String (2..50 chars including whitespaces)",
    "email": "String (2..100 chars, unique, should include exactly one @)",
    "phone": "String (6..14 chars, only digits, should start from +, optional field)"
  }
  ```
- **Response Body:**
  ```json
  {
    "id": "Long",
    "fullName": "String",
    "email": "String",
    "phone": "String"
  }
  ```

### Read All Customers
- **Method:** `GET`
- **URL:** `/api/customers`
- **Response Body:** List of customer objects with the following structure:
  ```json
  {
    "id": "Long",
    "fullName": "String",
    "email": "String",
    "phone": "String"
  }
  ```
- **Additional:** Customers can be optionally filtered to exclude inactive ones.

### Read Customer
- **Method:** `GET`
- **URL:** `/api/customers/{id}`
- **Response Body:** Customer object with the following structure:
  ```json
  {
    "id": "Long",
    "fullName": "String",
    "email": "String",
    "phone": "String"
  }
  ```

### Update Customer
- **Method:** `PUT`
- **URL:** `/api/customers/{id}`
- **Content-Type:** `application/json`
- **Body:**
  ```json
  {
    "id": "Long",
    "fullName": "String (2..50 chars including whitespaces)",
    "email": "String (not editable)",
    "phone": "String (6..14 chars, only digits, should start from +)"
  }
  ```
- **Response Body:** Updated customer object with the following structure:
  ```json
  {
    "id": "Long",
    "fullName": "String",
    "email": "String",
    "phone": "String"
  }
  ```

### Delete Customer
- **Method:** `DELETE`
- **URL:** `/api/customers/{id}`
- **Action:** Marks the customer as deleted without removing their data from the database.

## Database Structure

### customer Table
- **id:** bigint (Primary Key)
- **created:** bigint
- **updated:** bigint
- **full_name:** varchar
- **email:** varchar
- **phone:** varchar (nullable)
- **is_active:** bool
