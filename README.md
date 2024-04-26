# Fast Food App Backend

This repository contains the backend code for the Fast Food App, a web application for managing orders and restaurants.

## Technologies Used

- **Java**: Programming language used for backend development.
- **Spring Boot**: Framework used for building and running Java-based applications.
- **Spring Data JPA**: Provides easy implementation of JPA-based repositories.
- **PostgreSQL**: Database management system used for storing application data.
- **Maven**: Build automation tool used for managing project dependencies and building the application.
- **Docker**: Containerization platform used for packaging and deploying the application.

## Project Structure

The project structure is organized as follows:

- **src/main/java/com/fastfoodapp**: Root package for Java source files.
    - **controller**: Contains classes responsible for handling HTTP requests and responses.
    - **dto**: Contains Data Transfer Objects (DTOs) used to transfer data between layers.
    - **model**: Contains entity classes representing database tables.
    - **repository**: Contains repository interfaces for interacting with the database.
    - **service**: Contains service classes implementing business logic.
- **src/main/resources**: Contains application properties and configuration files.
- **Dockerfile**: Contains instructions for building a Docker image of the application.

## Endpoints

### OrderController

#### GET /api/orders/get/{id}

- Description: Retrieves an order by its ID.
- Request Parameters:
    - `id` (Path Variable): ID of the order to retrieve.
- Response:
    - `200 OK`: Returns the order DTO if found.
    - `404 Not Found`: If the order with the specified ID is not found.

#### PUT /api/orders/update/{id}

- Description: Updates an existing order.
- Request Parameters:
    - `id` (Path Variable): ID of the order to update.
- Request Body: JSON object representing the updated order DTO.
- Response:
    - `200 OK`: Returns the updated order DTO.

#### DELETE /api/orders/delete/{id}

- Description: Deletes an order by its ID.
- Request Parameters:
    - `id` (Path Variable): ID of the order to delete.
- Response:
    - `200 OK`: If the order is deleted successfully.

### RestaurantController

#### GET /api/restaurants/get/{id}

- Description: Retrieves a restaurant by its ID.
- Request Parameters:
    - `id` (Path Variable): ID of the restaurant to retrieve.
- Response:
    - `200 OK`: Returns the restaurant DTO if found.
    - `404 Not Found`: If the restaurant with the specified ID is not found.

#### PUT /api/restaurants/update/{id}

- Description: Updates an existing restaurant.
- Request Parameters:
    - `id` (Path Variable): ID of the restaurant to update.
- Request Body: JSON object representing the updated restaurant DTO.
- Response:
    - `200 OK`: Returns the updated restaurant DTO.

#### DELETE /api/restaurants/delete/{id}

- Description: Deletes a restaurant by its ID.
- Request Parameters:
    - `id` (Path Variable): ID of the restaurant to delete.
- Response:
    - `200 OK`: If the restaurant is deleted successfully.

### UserController

#### GET /api/users/managers

- Description: Retrieves a list of restaurant managers.
- Response:
    - `200 OK`: Returns a list of manager names.

## Running the Application

To run the application locally, follow these steps:

1. Make sure you have Java, Maven, and Docker installed on your system.
2. Clone this repository to your local machine.
3. Navigate to the project directory.
4. Build the Docker image using the provided Dockerfile: `docker build -t fastfood-backend .`
5. Run the Docker container: `docker run -p 8080:8080 fastfood-backend`
6. The application will be accessible at `http://localhost:8080`.

## API Documentation

The API documentation can be accessed using Swagger UI. After running the application locally, navigate to `http://localhost:8080/swagger-ui.html` to view the API documentation.

## Environment Variables

Ensure that you have the following environment variables configured:

- `POSTGRES_USERNAME`: Username for PostgreSQL database.
- `POSTGRES_PASSWORD`: Password for PostgreSQL database.
- `POSTGRES_DB`: Name of the PostgreSQL database.
- `PGADMIN_DEFAULT_EMAIL`: Default email for PGAdmin.
- `PGADMIN_DEFAULT_PASSWORD`: Default password for PGAdmin.
- `SPRING_DATASOURCE_URL`: URL of the database for Spring Data Source.
- `SPRING_DATASOURCE_USERNAME`: Username for Spring Data Source.
- `SPRING_DATASOURCE_PASSWORD`: Password for Spring Data Source.

## Initializer

The `UserInitializer` class is responsible for initializing user accounts in the application. The following credentials can be used to log in:

### Customer

- **Username**: kerim
- **Password**: pass1

- **Username**: ata
- **Password**: pass4

- **Username**: zeynep
- **Password**: pass7

- **Username**: kaan
- **Password**: pass10

### Restaurant Manager

- **Username**: erdem
- **Password**: pass2

- **Username**: nesrin
- **Password**: pass5

- **Username**: sezen
- **Password**: pass8

- **Username**: cangul
- **Password**: pass11

### General Manager

- **Username**: halil
- **Password**: pass3

- **Username**: muhammed
- **Password**: pass6

- **Username**: emre
- **Password**: pass9

- **Username**: meryem
- **Password**: pass12

Please use the appropriate credentials based on the role you want to log in with.

