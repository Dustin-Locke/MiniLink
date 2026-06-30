# MiniLink

MiniLink is a lightweight URL shortening service built with Java, Spring Boot, and a modern frontend UI. It provides a REST API for creating shortened URLs and a user interface for managing links, including quick actions like opening, copying, and deleting links.

The application uses PostgreSQL for persistence and Flyway for database version control.

## Features

Backend
- Create shortened URLs from long web addresses
- Generate unique short codes automatically
- Create custom aliases for memorable links (e.g. `/google`)
- Automatically redirect users from short links to their original destinations
- Validate submitted URLs before saving
- Persist data using PostgreSQL and Spring Data JPA
- Version database changes using Flyway migrations
- Explore and test endpoints through Swagger/OpenAPI documentation
- Optional custom aliases instead of generated code

Frontend
- Create MiniLinks from a simple UI form
- View all created links in a dynamic list
- One-click actions per link:
- - Open link (direct redirect)
- - Copy short URL to clipboard
- - Delete link
- Real-time list updates after creation/deletion

## Tech Stack

Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway
- Maven
- Swagger / OpenAPI
- Lombok

Frontend
- HTML
- CSS
- Angular
- Typescript
- Fetch API for backend communication

## API Endpoints

### Create a Short Link

**POST** `/api`

Request:

```json
{
    "originalUrl": "https://www.google.com"
}
```

Response:

```json
{
    "miniCode": "26981388",
    "originalUrl": "https://www.google.com"
}
```

---

### Redirect to Original URL

**GET** `/{miniCode}`

Example:

```text
http://localhost:8080/api/26981388
```

The application responds with an HTTP redirect to the original URL.

### List All Links
**Get** `/api`

Response:

```json
{
    "miniCode": "26981388",
    "originalUrl": "https://www.google.com"
}
```

---

## Frontend Overview

The frontend provides a simple dashboard for managing MiniLinks.

## Main Features

## Create Link Forms
Users can paste long URL and generate MiniLink instantly.

## Link List
Each created link appears in a list with the following actions:
- Open – navigates directly to the original URL via backend redirect
- Copy – copies the short URL to clipboard
- Delete – removes the MiniLink from the system

The list updates automatically after any action.

---

## Database Migrations

This project uses Flyway to manage database schema changes.

Migration scripts are located in:

```text
src/main/resources/db/migration
```

Flyway automatically applies any pending migrations when the application starts.

---

## Running Backend Locally

### Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL

### 1. Create the Database

Connect to PostgreSQL:

```bash
psql -U postgres
```

Create the database:

```sql
CREATE DATABASE minilink;
```

Exit psql:

```sql
\q
```

---

### 2. Configure Local Properties

Using IntelliJ, add you local username and password to the `Environment Variables`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/minilink
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=validate
spring.flyway.enabled=true
```

---

### 3. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run `MiniLinkApplication` directly from your IDE.

---

### 4. Access Swagger Documentation

Open:

```text
http://localhost:8080/swagger-ui/index.html
```

The OpenAPI specification is available at:

```text
http://localhost:8080/v3/api-docs
```

---

## Running Frontend Locally

The frontend is built with Angular and runs using the Angular CLI (ng serve), serving the application on http://localhost:4200.

### Navigate to frontend

```bash
cd MiniLink/mini-link-web
```

### Install dependencies

```bash
npm install
```

This will install all required packages listed in package.json

### Start angular dev server

```bash
ng serve
```

### Access the frontend

```bash
http://localhost:4200
```

Angular’s default dev server runs on 4200, not 3000.

---

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── locke/dustin/minilink
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── repository
│   │       └── service
│   └── resources
│       └── db
│           └── migration
└── test
```

---

## Future Enhancements

- Base62 short code generation
- Link expiration dates
- Click analytics and usage tracking
- Unit and integration tests
- Docker support
- Authentication (user-specific links)
- Drag-and-drop link organization

---

## License

This project is available under the MIT License.
