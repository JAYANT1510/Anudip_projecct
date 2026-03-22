CREATE DATABASE IF NOT EXISTS lms_db;
USE lms_db;

-- Table for Users (Students and Admins)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role ENUM('STUDENT', 'ADMIN') DEFAULT 'STUDENT',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for Courses
CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    instructor_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (instructor_id) REFERENCES users(id) ON DELETE SET NULL
);

-- Table for Enrollments
CREATE TABLE IF NOT EXISTS enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    course_id INT,
    enrollment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Table for Lessons
CREATE TABLE IF NOT EXISTS lessons (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT,
    course_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Insert sample data
/*INSERT INTO users (username, password, email, role) VALUES 
('admin', 'admin123', 'admin@lms.com', 'ADMIN'),
('student1', 'pass123', 'student1@lms.com', 'STUDENT');

INSERT INTO courses (title, description, instructor_id) VALUES 
('Java Programming', 'Learn Java from scratch', 1),
('Database Management', 'Master MySQL and Database Design', 1);
*/
INSERT INTO lessons (title, content, course_id) VALUES 
('Introduction to Java', 'Java is a high-level, class-based, object-oriented programming language.', 1),
('Variables and Data Types', 'Learn about primitive types and object types in Java.', 1),
('SQL Basics', 'Structured Query Language (SQL) is used for managing databases.', 2);
