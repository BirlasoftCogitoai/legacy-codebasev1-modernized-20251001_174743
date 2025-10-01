```sql
-- Legacy EGP Database Schema
-- Target: PostgreSQL 12 or higher / Oracle 19c

-- Drop tables if they exist (for clean reinstall)
DROP TABLE IF EXISTS egp_notes CASCADE;
DROP TABLE IF EXISTS egp_case_records CASCADE;
DROP TABLE IF EXISTS egp_customers CASCADE;
DROP TABLE IF EXISTS egp_users CASCADE;
DROP TABLE IF EXISTS egp_audit_log CASCADE;

-- Users table (modernized security with BCrypt)
CREATE TABLE egp_users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(60) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Customers table
CREATE TABLE egp_customers (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Case records table
CREATE TABLE egp_case_records (
    case_id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES egp_customers(customer_id) ON DELETE CASCADE,
    case_number VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    status VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Audit log table
CREATE TABLE egp_audit_log (
    log_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES egp_users(user_id) ON DELETE SET NULL,
    action VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Notes table
CREATE TABLE egp_notes (
    note_id SERIAL PRIMARY KEY,
    case_id INTEGER NOT NULL REFERENCES egp_case_records(case_id) ON DELETE CASCADE,
    note TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```