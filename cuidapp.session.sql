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


CREATE TABLE cared_people (
    id SERIAL PRIMARY KEY,  -- Auto-incrementing primary key
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    user_id INT,
    conditions JSONB,  -- JSONB field for storing an array of conditions
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Example of inserting a record with conditions
INSERT INTO cared_people (name, age, user_id, conditions)
VALUES ('Alice', 65, 1, '["diabetes", "high blood pressure"]');