CREATE TABLE Employee (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          salary DOUBLE PRECISION NOT NULL,
                          manager_id INT
);

ALTER TABLE Employee ADD CONSTRAINT fk_employee_manager FOREIGN KEY (manager_id) REFERENCES Employee(id);
