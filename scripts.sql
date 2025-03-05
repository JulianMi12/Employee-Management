create database employee_management;
use employees_management;

-- TABLES
create table employee (
	id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(80),
    last_name varchar(80),
    salary decimal(19,2)
);