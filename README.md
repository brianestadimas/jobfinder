# Simple Jobs Information System

## Getting Started

### Overview

![alt text](https://i.ibb.co/DLz536T/Screenshot-79.png)

![alt text](https://i.ibb.co/6FDN276/Screenshot-80.png)

### Language

Java 8.0

### Framework

Springboot as backend

Thymeleaf as frontend

## Run at Localhost

1. Pull from master
2. Download Spring tools suite (STS) here (https://spring.io/tools)
3. Download XAMPP (https://www.apachefriends.org/download.html)
4. Run XAMPP Control Panel (Start Apache and MySQL)
5. Open (localhost/phpmyadmin) in browser
6. Create database named "joblist"
7. Run STS and Open Project From Folder
8. Maven Build
9. Right click on root project folder then "Run as Springboots"
10. Inject SQL for superadmin in localhost/phpmyadmin :

username : admin
password : admin

INSERT INTO `account` (`id`, `name`, `password`, `role`, `username`) VALUES
(1, 'admin', '$2a$10$ldYxhf9lQJgatap6ieoU1uJXEWE1aR00NfVB82sjUL69q0pF7vu8i', 'admin', 'admin');

## Author

Brian Estadimas

