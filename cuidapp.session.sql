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



CREATE VIEW v_cared_people AS
SELECT
  cp.name,
  DATE_PART('year', AGE(cp.date_of_birth)) AS age,
  u.email AS carer_email
FROM cared_people cp
JOIN users u ON cp.carer_id = u.id;

-- Example of inserting a record with conditions
INSERT INTO cared_people (name, age, user_id, conditions)
VALUES ('Alice', 65, 1, '["diabetes", "high blood pressure"]');