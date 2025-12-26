# Bank Management System

A professional, production-ready Bank Management System backend built with Spring Boot, featuring JWT authentication, role-based access control, secure transactions, and comprehensive admin features.

## 🚀 Features

### Authentication & Authorization
- **JWT-based Authentication**: Secure token-based authentication
- **Role-Based Access Control**: Customer and Admin roles with appropriate permissions
- **User Registration & Login**: Secure user management with password encryption

### Account Management
- **Multiple Account Types**: Support for Savings and Current accounts
- **Account Creation**: Users can create multiple accounts
- **Account Status Management**: Active, Frozen, and Closed account states
- **Balance Tracking**: Real-time balance updates with transaction history

### Transaction System
- **Deposits**: Secure deposit transactions with validation
- **Withdrawals**: Balance-verified withdrawal operations
- **Fund Transfers**: Inter-account transfers with debit/credit ledger entries
- **Transaction History**: Comprehensive transaction records with pagination, search, and filtering
- **Idempotent APIs**: Prevents duplicate transactions using idempotency keys
- **Daily Transaction Limits**: Configurable daily limits per account
- **Transaction Integrity**: Atomic operations ensuring data consistency

### Admin Features
- **User Management**: View and manage all users
- **Account Freezing**: Freeze/unfreeze accounts for security
- **Audit Logs**: Complete audit trail of all system actions
- **Transaction Monitoring**: Monitor all transactions across the system

### Technical Features
- **Clean Architecture**: Separation of concerns with layered architecture
- **RESTful API Design**: Standard REST endpoints with proper HTTP methods
- **Centralized Error Handling**: Global exception handler with meaningful error messages
- **Input Validation**: Comprehensive validation using Bean Validation
- **Logging**: Structured logging throughout the application
- **Unit Tests**: Test coverage for critical business logic
- **Docker Support**: Containerized deployment with Docker Compose

## 🛠️ Tech Stack

- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Database**: PostgreSQL 15
- **Security**: Spring Security with JWT
- **Build Tool**: Maven
- **Containerization**: Docker & Docker Compose
- **Testing**: JUnit 5, Mockito

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 15 (or use Docker Compose)
- Docker & Docker Compose (optional, for containerized deployment)

## 🔧 Installation & Setup

### Option 1: Using Docker Compose (Recommended)

1. Clone the repository:
```bash
git clone https://github.com/Adiregde/bank-management-system.git
cd bank-management-system
```

2. Start the application:
```bash
docker-compose up -d
```

The application will be available at `http://localhost:8080`

### Option 2: Local Development

1. Clone the repository:
```bash
git clone https://github.com/Adiregde/bank-management-system.git
cd bank-management-system
```

2. Create PostgreSQL database:
```sql
CREATE DATABASE bankdb;
```

3. Update `application.yml` with your database credentials if needed:
```yaml
spring:
  datasource:
    username: your_username
    password: your_password
```

4. Build and run:
```bash
mvn clean install
mvn spring-boot:run
```

## 📚 API Documentation

### Authentication Endpoints

#### Register User
```http
POST /api/auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe"
}
```

#### Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "type": "Bearer",
  "userId": 1,
  "username": "john_doe",
  "role": "CUSTOMER"
}
```

### Account Endpoints

#### Create Account
```http
POST /api/accounts
Authorization: Bearer {token}
Content-Type: application/json

{
  "accountType": "SAVINGS"
}
```

#### Get User Accounts
```http
GET /api/accounts
Authorization: Bearer {token}
```

#### Get Account by Number
```http
GET /api/accounts/{accountNumber}
Authorization: Bearer {token}
```

### Transaction Endpoints

#### Deposit
```http
POST /api/transactions/deposit
Authorization: Bearer {token}
Content-Type: application/json

{
  "accountNumber": "1234567890123456",
  "amount": 1000.00,
  "description": "Initial deposit",
  "idempotencyKey": "unique-key-123"
}
```

#### Withdraw
```http
POST /api/transactions/withdraw
Authorization: Bearer {token}
Content-Type: application/json

{
  "accountNumber": "1234567890123456",
  "amount": 500.00,
  "description": "Cash withdrawal",
  "idempotencyKey": "unique-key-456"
}
```

#### Transfer
```http
POST /api/transactions/transfer
Authorization: Bearer {token}
Content-Type: application/json

{
  "fromAccountNumber": "1234567890123456",
  "toAccountNumber": "9876543210987654",
  "amount": 200.00,
  "description": "Payment for services",
  "idempotencyKey": "unique-key-789"
}
```

#### Get Transaction History
```http
GET /api/transactions/history/{accountNumber}?page=0&size=20&type=DEPOSIT&startDate=2024-01-01&endDate=2024-12-31&searchTerm=deposit
Authorization: Bearer {token}
```

### Admin Endpoints

#### Get All Users
```http
GET /api/admin/users?page=0&size=20
Authorization: Bearer {admin_token}
```

#### Update User Status
```http
PUT /api/admin/users/{userId}/status?enabled=false
Authorization: Bearer {admin_token}
```

#### Freeze Account
```http
PUT /api/admin/accounts/{accountNumber}/freeze
Authorization: Bearer {admin_token}
```

#### Unfreeze Account
```http
PUT /api/admin/accounts/{accountNumber}/unfreeze
Authorization: Bearer {admin_token}
```

## 🔐 Security Features

- **Password Encryption**: BCrypt password hashing
- **JWT Tokens**: Secure token-based authentication
- **Role-Based Access**: Admin and Customer role separation
- **Input Validation**: Comprehensive request validation
- **SQL Injection Protection**: JPA/Hibernate parameterized queries
- **Audit Logging**: Complete audit trail of all actions

## 📊 Database Schema

The system uses the following main entities:
- **Users**: User accounts with authentication details
- **Accounts**: Bank accounts with balance and status
- **Transactions**: All financial transactions with ledger entries
- **AuditLogs**: System-wide audit trail
- **DailyTransactionLimits**: Daily transaction tracking

## 🧪 Testing

Run tests with:
```bash
mvn test
```

Test coverage includes:
- Authentication service tests
- Transaction service tests
- Account service tests
- Security configuration tests

## 📝 Configuration

Key configuration in `application.yml`:

```yaml
bank:
  transaction:
    daily-limit: 100000.00
    min-deposit: 100.00
    min-withdrawal: 100.00
    min-transfer: 50.00
    max-transfer: 50000.00

jwt:
  secret: your-secret-key
  expiration: 86400000  # 24 hours
```

## 🚢 Deployment

### Production Deployment

1. Set environment variables:
```bash
export DB_USERNAME=your_db_user
export DB_PASSWORD=your_db_password
export JWT_SECRET=your-256-bit-secret-key
```

2. Build Docker image:
```bash
docker build -t bank-management-system .
```

3. Run with Docker Compose:
```bash
docker-compose up -d
```

## 📄 License

This project is open source and available under the MIT License.

## 👤 Author

**Adiregde**

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

## ⭐ Acknowledgments

- Spring Boot team for the excellent framework
- PostgreSQL community for the robust database
- All open-source contributors
