-- Create users table
CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL
);

TRUNCATE TABLE users;

-- Insert a user into the table
INSERT INTO users (email, password) 
VALUES ('juanperez@gmail.com', '$2a$10$slSrGAakc50EWi/k0FGDqO73beKnPA.U/wEjIRBl/iq3qJrwBaSde');