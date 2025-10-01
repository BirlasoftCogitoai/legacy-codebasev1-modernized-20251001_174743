```sql
-- Legacy EGP Database Seed Data
-- Sample data for development and testing

-- Insert sample users (passwords are BCrypt hashed)
-- BCrypt hash for 'password' is '$2a$10$D9yQ4bI9N7Wq14Y5d7m4QO1kmot8v5Oqk1QeF1X5/5UmF8KkUj0yW'
INSERT INTO egp_users (username, password_hash, email, first_name, last_name, role, active) VALUES
('admin', '$2a$10$D9yQ4bI9N7Wq14Y5d7m4QO1kmot8v5Oqk1QeF1X5/5UmF8KkUj0yW', 'admin@egp.gov', 'System', 'Administrator', 'ADMIN', TRUE),
('jsmith', '$2a$10$D9yQ4bI9N7Wq14Y5d7m4QO1kmot8v5Oqk1QeF1X5/5UmF8KkUj0yW', 'john.smith@egp.gov', 'John', 'Smith', 'USER', TRUE);

-- Insert sample customers
INSERT INTO egp_customers (first_name, last_name, email, phone) VALUES
('Alice', 'Wonderland', 'alice@wonderland.com', '123-456-7890'),
('Bob', 'Builder', 'bob@builder.com', '098-765-4321');

-- Insert sample case records
INSERT INTO egp_case_records (customer_id, case_number, description, status) VALUES
(1, 'CASE-001', 'Sample case description for Alice', 'Open'),
(2, 'CASE-002', 'Sample case description for Bob', 'Closed');

-- Insert sample notes
INSERT INTO egp_notes (case_id, note) VALUES
(1, 'Initial note for Alice\'s case'),
(2, 'Initial note for Bob\'s case');
```