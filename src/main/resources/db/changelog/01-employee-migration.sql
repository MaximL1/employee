CREATE TABLE Employee (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          salary DOUBLE PRECISION NOT NULL,
                          unique_code VARCHAR(255) NOT NULL UNIQUE,
                          manager_id INTEGER
);

ALTER TABLE Employee ADD CONSTRAINT fk_employee_manager FOREIGN KEY (manager_id) REFERENCES Employee(id);
