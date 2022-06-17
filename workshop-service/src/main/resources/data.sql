INSERT INTO staff(id, first_name, last_name)
VALUES ('HUBE', 'Franz', 'Huber'),
       ('SCMI', 'Barbara', 'Schmidt'),
       ('BAUE', 'Fritz', 'Bauer');

INSERT INTO tasks (description, finished_date, hours_worked, employee_id)
VALUES ('Implementierung JUnit Tests', '2019-05-17', 120, 'HUBE'),
       ('Erstellung UML-Diagramm', '2019-05-17', 90, 'SCMI'),
       ('Projektmeeting', '2019-05-18', 60, 'HUBE'),
       ('Projektmeeting', '2019-05-18', 60, 'SCMI'),
       ('Projektmeeting', '2019-05-18', 60, 'BAUE'),
       ('Implementierung', '2019-05-19', 350, 'BAUE'),
       ('Implementierung', '2019-05-19', 420, 'SCMI'),
       ('Tests und Bugfixes', '2019-05-19', 300, 'HUBE');