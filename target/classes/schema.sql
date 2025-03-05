CREATE DATABASE IF NOT EXISTS employees_management;
USE employees_management;

-- TABLES
CREATE TABLE IF NOT EXISTS employee
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(80),
    last_name VARCHAR(80),
    last_name VARCHAR(80),
    salary    DECIMAL(19, 2)
);
