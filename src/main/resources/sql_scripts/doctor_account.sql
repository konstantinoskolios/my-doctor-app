CREATE TABLE IF NOT EXISTS doctor_account
(
    id         serial primary key,
    email      VARCHAR(50) unique not null,
    pass       varchar(50)        not null,
    first_name varchar(50)        not null,
    last_name  varchar(50)        not null,
    speciality varchar(50)        not null
);


insert into doctor_account(id, email, pass, first_name, last_name, speciality)
values (1, 'johnsmith@mydoctorapp.com', '1234', 'John', 'Smith', 'Cardiologist');

