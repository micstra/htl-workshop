drop table if exists tasks;
drop table if exists staff;


CREATE TABLE staff
(
    id VARCHAR(6) NOT NULL,
    first_name  VARCHAR(255),
    last_name VARCHAR(255),
    CONSTRAINT staff_pkey PRIMARY KEY (id)
);

CREATE TABLE tasks
(
    id      SERIAL     NOT NULL,
    description  VARCHAR(255),
    finished_date     DATE,
    hours_worked INTEGER,
    employee_id     VARCHAR(6) NOT NULL,
    CONSTRAINT tasks_pkey PRIMARY KEY (id),
    CONSTRAINT fk_tasks_employee_id FOREIGN KEY (employee_id)
        REFERENCES staff (id)
);