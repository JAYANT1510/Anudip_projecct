# Learning Management System (LMS)

A full-stack LMS project built with **Java (Spring Boot)** for the backend and **React** for the frontend, using **MySQL** as the database.

## Project Structure
- `backend/`: Spring Boot application
- `frontend/`: React application
- `db.sql`: Database schema and sample data

## Prerequisites
- Java 17 or later
- Maven
- Node.js and npm
- MySQL Server

## Setup Instructions

### 1. Database Setup
1. Open your MySQL client (e.g., MySQL Workbench).
2. Execute the script in `db.sql` to create the database and tables.

### 2. Backend Setup
1. Navigate to the `backend/` folder.
2. Update `src/main/resources/application.properties` with your MySQL username and password.
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```
   The backend will run on `http://localhost:8080`.

### 3. Frontend Setup
1. Navigate to the `frontend/` folder.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Run the React app:
   ```bash
   npm start
   ```
   The frontend will run on `http://localhost:3000`.

## Features
- **User Roles**: ADMIN and STUDENT roles.
- **Course Management**: CRUD operations for courses (Create, Read, Delete).
- **Enrollment System**: Students can enroll in available courses.
- **Lesson Management**: Courses now support multiple lessons with content.
- **Admin Panel**: Dedicated dashboard for administrators to manage courses.
- **LMS Dashboard**: A modern React interface to explore, enroll, and view course content.
- **API Documentation**: Integrated Swagger/OpenAPI for easy API exploration.
- **Error Handling**: Global exception handling for consistent API error responses.
- **CORS Support**: Backend is configured to allow requests from the React frontend.

## API Documentation
Once the backend is running, you can access the Swagger UI at:
`http://localhost:8080/swagger-ui/index.html`
"# Anudip_projecct" 
"# Anudip_projecct" 
