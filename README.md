# MiniLink

MiniLink is a lightweight URL shortening service built with Java and Spring Boot. It provides a REST API for creating shortened URLs and redirecting users to their original destinations. The application uses PostgreSQL for persistence and Flyway for database version control.

## Features

- Create shortened URLs from long web addresses
- Generate unique short codes automatically
- Create custom aliases for memorable links (e.g. `/google`)
- Automatically redirect users from short links to their original destinations
- Validate submitted URLs before saving
- Persist data using PostgreSQL and Spring Data JPA
- Version database changes using Flyway migrations
- Explore and test endpoints through Swagger/OpenAPI documentation

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway
- Maven
- Swagger / OpenAPI
- Lombok

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

---

## Database Migrations

This project uses Flyway to manage database schema changes.

Migration scripts are located in:

```text
src/main/resources/db/migration
```

Flyway automatically applies any pending migrations when the application starts.

---

## Running Locally

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

- Custom aliases (e.g. `/google`)
- Base62 short code generation
- Link expiration dates
- Click analytics and usage tracking
- Unit and integration tests
- Docker support

---

## License

This project is available under the MIT License.
