drop table cared_people;

CREATE TABLE cared_people (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  date_of_birth DATE NOT NULL,
  gender TEXT CHECK (gender IN ('male', 'female', 'other')),
  address TEXT,
  carer_id INTEGER NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  -- Foreign key constraint to ensure carer_id refers to a valid user
  CONSTRAINT fk_carer
    FOREIGN KEY (carer_id)
    REFERENCES users(id)
    ON DELETE CASCADE
);


CREATE VIEW v_cared_people AS
SELECT
  cp.name,
  DATE_PART('year', AGE(cp.date_of_birth)) AS age,
  u.email AS carer_email
FROM cared_people cp
JOIN users u ON cp.carer_id = u.id;