-- Create users table
CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL
);

-- Insert a user into the table
INSERT INTO users (email, password) 
VALUES ('juanperez@gmail.com', '$2a$10$slSrGAakc50EWi/k0FGDqO73beKnPA.U/wEjIRBl/iq3qJrwBaSde');

CREATE TABLE cared_people (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  date_of_birth DATE NOT NULL,
  gender TEXT CHECK (gender IN ('male', 'female', 'other')),
  address TEXT,
  carer_id INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_carer
    FOREIGN KEY (carer_id)
    REFERENCES users(id)
    ON DELETE CASCADE
);

CREATE VIEW v_cared_people AS
SELECT
  cp.id,
  cp.name,
  DATE_PART('year', AGE(cp.date_of_birth)) AS age,
  u.email AS carer_email
FROM cared_people cp
JOIN users u ON cp.carer_id = u.id;

-- Example of inserting a record with conditions
INSERT INTO cared_people (name, age, user_id, conditions)
VALUES ('Alice', 65, 1, '["diabetes", "high blood pressure"]');

CREATE TABLE IF NOT EXISTS user_activations (
  id SERIAL PRIMARY KEY,
  email TEXT NOT NULL,
  name TEXT NOT NULL,
  password_hash TEXT NOT NULL,
  token TEXT NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  expires_at TIMESTAMP NOT NULL,
  activated_at TIMESTAMP
);

CREATE TABLE diagnosis (
  id SERIAL PRIMARY KEY,
  cared_person_id INTEGER NOT NULL,
  diagnosis_date DATE NOT NULL,
  diagnosis TEXT NOT NULL,
  evolution TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_cared_person
    FOREIGN KEY (cared_person_id)
    REFERENCES cared_people(id)
    ON DELETE CASCADE
);

CREATE TABLE medications (
  id SERIAL PRIMARY KEY,
  cared_person_id INTEGER NOT NULL,
  name TEXT NOT NULL,
  dosage TEXT,             -- e.g., '10 mg', '850 mg'
  frequency TEXT,          -- e.g., '1 vez al día'
  instructions TEXT,       -- Optional: e.g., 'antes del desayuno'
  is_current BOOLEAN DEFAULT TRUE, -- true = actuales, false = pasados
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_cared_person
    FOREIGN KEY (cared_person_id)
    REFERENCES cared_people(id)
    ON DELETE CASCADE
);
