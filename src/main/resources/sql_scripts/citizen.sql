CREATE TABLE IF NOT EXISTS citizen
(
    first_name             VARCHAR(30),
    last_name              VARCHAR(30),
    father_first_name      VARCHAR(30),
    tax_number             VARCHAR(9) UNIQUE,
    social_security_number VARCHAR(11) UNIQUE,
    phone_number           VARCHAR(10) UNIQUE,
    birthdate              VARCHAR(255)
);

alter table citizen
    add column id serial primary key;

