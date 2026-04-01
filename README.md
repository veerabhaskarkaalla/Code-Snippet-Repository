# 📝 Code Snippet Repository

A modern, feature-rich web application for managing, organizing, and sharing code snippets. Built with Spring Boot and featuring an AI-powered chatbot assistant.

![Java Version](https://img.shields.io/badge/Java-17+-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-orange.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

## ✨ Features

- 🔐 **User Authentication** - Secure login and registration system
- 📝 **Code Snippet Management** - Create, read, update, delete code snippets
- 🏷️ **Language Categorization** - Support for Java, Python, JavaScript, HTML, CSS, SQL
- 🔍 **Smart Search** - Search snippets by title, description, or content
- 📊 **Dashboard Analytics** - View statistics and recent activity
- 🤖 **AI Chatbot Assistant** - Get help with code formatting, explanation, optimization, and debugging
- 🎨 **Modern UI** - Clean, responsive dark theme inspired by GitHub
- 📱 **Mobile Responsive** - Works on all devices

## 🛠️ Technology Stack

### Backend
- **Java 17** - Core programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Database operations
- **Hibernate** - ORM framework
- **MySQL** - Relational database

### Frontend
- **Thymeleaf** - Template engine
- **HTML5/CSS3** - Structure and styling
- **JavaScript** - Client-side interactivity
- **GitHub Dark Theme** - Modern UI design
- **Responsive Design** - Mobile-friendly layout

### AI Features
- **AI Chatbot** - Code assistance and formatting
- **Mock AI Responses** - Ready for OpenAI integration

## 📋 Prerequisites

Before running this project, make sure you have the following installed:

- **Java JDK 17 or higher**
  ```bash
  java -version
MySQL 8.0 or higher

bash
mysql --version
Maven 3.6+ (or use included Maven wrapper)

bash
mvn -version
STS (Spring Tool Suite) or IntelliJ IDEA (Recommended)

🚀 Installation & Setup
Step 1: Clone the Repository
bash
git clone https://github.com/yourusername/code-snippet-repository.git
cd code-snippet-repository
Step 2: Configure Database
Start MySQL Server

bash
# On Windows (if using XAMPP)
# Start Apache and MySQL from XAMPP Control Panel

# On Linux/Mac
sudo systemctl start mysql
Create Database

sql
-- Login to MySQL
mysql -u root -p

-- Create database
CREATE DATABASE snippet_db;

-- Verify
SHOW DATABASES;
Step 3: Configure Application Properties
Open src/main/resources/application.properties and update the database configuration:

properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/snippet_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE  # Change this
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Settings
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Server
server.port=8080

# Thymeleaf
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false

# Logging
logging.level.com.snippets=DEBUG
Step 4: Build the Project
bash
# Using Maven wrapper (Windows)
mvnw clean install

# Using Maven wrapper (Linux/Mac)
./mvnw clean install

# Or using local Maven
mvn clean install
Step 5: Run the Application
Option A: Using STS (Spring Tool Suite)
Open STS

Import project: File → Import → Existing Maven Projects

Select the project folder

Right-click project → Run As → Spring Boot App

Option B: Using Command Line
bash
# Run directly
mvn spring-boot:run

# Or run the JAR file
java -jar target/code-snippet-repository-1.0.0.jar
Option C: Using IntelliJ IDEA
Open IntelliJ IDEA

File → Open → Select project folder

Run the CodeSnippetManagerApplication class

Step 6: Access the Application
Open your browser and navigate to:

text
http://localhost:8080
🎯 Application URLs
Page	URL	Description
Home	/	Landing page
Register	/register	Create new account
Login	/login	Sign in to account
Dashboard	/dashboard	User dashboard with statistics
My Snippets	/snippets	View all snippets
Create Snippet	/snippets/create	Add new code snippet
Edit Snippet	/snippets/edit/{id}	Modify existing snippet
Search	/snippets/search?keyword={query}	Search snippets
🤖 AI Chatbot Features
The AI chatbot (located at bottom-right corner) can help with:

Code Formatting - Beautify and indent code

Code Explanation - Understand complex code

Optimization - Get performance suggestions

Debugging - Find and fix errors

Code Generation - Create boilerplate code

Quick Actions
Click the 🤖 icon to open chatbot

Use quick action buttons for common tasks

Type your question naturally

📁 Project Structure
text
code-snippet-repository/
├── src/
│   ├── main/
│   │   ├── java/com/snippets/
│   │   │   ├── CodeSnippetManagerApplication.java
│   │   │   ├── controller/
│   │   │   │   ├── HomeController.java
│   │   │   │   ├── UserController.java
│   │   │   │   ├── SnippetController.java
│   │   │   │   └── DashboardController.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   ├── SnippetService.java
│   │   │   │   └── impl/
│   │   │   │       ├── UserServiceImpl.java
│   │   │   │       └── SnippetServiceImpl.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── SnippetRepository.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java
│   │   │   │   └── Snippet.java
│   │   │   └── config/
│   │   │       └── SecurityConfig.java
│   │   └── resources/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   ├── register.html
│   │       │   ├── dashboard.html
│   │       │   └── snippets/
│   │       │       ├── list-snippets.html
│   │       │       └── create-snippet.html
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── github-dark.css
│   │       │   └── js/
│   │       │       └── chatbot.js
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
🧪 Testing the Application
1. Register a New User
Navigate to /register

Fill in details: full name, username, email, password

Click "Create account"

2. Login
Navigate to /login

Enter username and password

Click "Sign in"

3. Create a Snippet
Go to Dashboard → "New Snippet"

Enter title, description, language, and code

Click "Create snippet"

4. View Snippets
Navigate to /snippets

See all your snippets

Edit or delete as needed

5. Search Snippets
On snippets page, use search box

Enter keyword to filter results

6. Use AI Chatbot
Click 🤖 icon at bottom right

Ask for help with code formatting

Try quick action buttons

🐛 Troubleshooting
Common Issues and Solutions
1. MySQL Connection Error
text
Error: Cannot load driver class: com.mysql.cj.jdbc.Driver
Solution:

Add MySQL dependency to pom.xml

Run mvn clean install

2. Database Not Found
text
Error: Unknown database 'snippet_db'
Solution:

sql
CREATE DATABASE snippet_db;
3. Port 8080 Already in Use
text
Error: Web server failed to start. Port 8080 was already in use.
Solution: Change port in application.properties:

properties
server.port=8081
4. Compilation Errors
text
Error: Unresolved compilation problem
Solution:

Clean project: mvn clean

Update Maven: mvn clean install

Refresh IDE project

5. Password Encoding Error
text
Error: Password encoding failed
Solution:

BCryptPasswordEncoder is already configured

Check password is properly encoded when registering

📊 Database Schema
Users Table
sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    created_at DATETIME
);
Snippets Table
sql
CREATE TABLE snippets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    code TEXT NOT NULL,
    language VARCHAR(255) NOT NULL,
    created_at DATETIME,
    updated_at DATETIME,
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
🔧 Configuration Options
Application Properties Explained
Property	Description	Default
spring.datasource.url	Database connection URL	jdbc:mysql://localhost:3306/snippet_db
spring.datasource.username	Database username	root
spring.datasource.password	Database password	-
server.port	Application port	8080
spring.jpa.hibernate.ddl-auto	Schema generation strategy	update
logging.level.com.snippets	Logging level	DEBUG<img width="1894" height="1096" alt="Screenshot 2026-04-01 170611" src="https://github.com/user-attachments/assets/150e4384-434e-4f90-aa12-21c6aac88e93" />
<img width="1919" height="1089" alt="Screenshot 2026-04-01 170430" src="https://github.com/user-attachments/assets/35c4b048-6af6-488f-8c86-e9a3202f3e5f" />
<img width="1915" height="1093" alt="Screenshot 2026-04-01 170507" src="https://github.com/user-attachments/assets/242b46d8-d750-4995-adcf-7d7fc609f428" />
<img width="1917" height="1086" alt="Screenshot 2026-04-01 170534" src="https://github.com/user-attachments/assets/7eb89211-7f79-4709-80e5-c0b4f31d2e7f" />
<img width="462" height="561" alt="Screenshot 2026-04-01 170543" src="https://github.com/user-attachments/assets/69a3ab02-e2f6-4c94-9b38-71b29c72465d" />
<img width="1910" height="1054" alt="Screenshot 2026-04-01 170555" src="https://github.com/user-attachments/assets/1bf252e6-dcb1-445b-a380-337544ab851a" />
<img width="1894" height="1096" alt="Screenshot 2026-04-01 170611" src="https://github.com/user-attachments/assets/b5eb3e06-cb1e-4fc0-9bf2-6eb3ce1916cd" />





