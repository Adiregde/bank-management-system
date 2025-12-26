# Bank Management System - Setup Guide

## Quick Start Options

### Option 1: Using Docker (Easiest - Recommended)

1. **Install Docker Desktop**
   - Download from: https://www.docker.com/products/docker-desktop/
   - Install and restart your computer
   - Make sure Docker Desktop is running

2. **Run the application**
   ```powershell
   cd C:\Users\adire\OneDrive\Desktop\Project\bank-management-system
   docker-compose up -d
   ```

3. **Access the application**
   - URL: http://localhost:8080
   - The application will be ready in 1-2 minutes

### Option 2: Local Development Setup

#### Prerequisites Installation:

1. **Install Java 17+**
   - Download from: https://adoptium.net/temurin/releases/
   - Choose Windows x64 JDK 17 or higher
   - Install and add to PATH
   - Verify: `java -version`

2. **Install Maven**
   - Download from: https://maven.apache.org/download.cgi
   - Extract to `C:\Program Files\Apache\maven`
   - Add `C:\Program Files\Apache\maven\bin` to PATH
   - Verify: `mvn -version`

3. **Install PostgreSQL**
   - Download from: https://www.postgresql.org/download/windows/
   - Install with default settings
   - Remember the password you set (default: postgres)
   - Create database: `CREATE DATABASE bankdb;`

4. **Run the application**
   ```powershell
   cd C:\Users\adire\OneDrive\Desktop\Project\bank-management-system
   mvn clean install
   mvn spring-boot:run
   ```

5. **Access the application**
   - URL: http://localhost:8080

## Testing the API

Once the application is running, you can test it using:

### 1. Register a User
```bash
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe"
}
```

### 2. Login
```bash
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

### 3. Create Account (use token from login)
```bash
POST http://localhost:8080/api/accounts
Authorization: Bearer {your_token_here}
Content-Type: application/json

{
  "accountType": "SAVINGS"
}
```

## API Testing Tools

You can use:
- **Postman**: https://www.postman.com/downloads/
- **Thunder Client** (VS Code extension)
- **curl** (command line)
- **Insomnia**: https://insomnia.rest/download

## Troubleshooting

- **Port 8080 already in use**: Change port in `application.yml` or stop the service using port 8080
- **Database connection error**: Make sure PostgreSQL is running
- **Docker issues**: Make sure Docker Desktop is running

