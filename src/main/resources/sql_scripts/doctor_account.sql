CREATE TABLE IF NOT EXISTS doctorAccount
(
    id         serial primary key,
    email      VARCHAR(50) unique not null,
    pass       varchar(50)        not null,
    firstName  varchar(50)        not null,
    lastName   varchar(50)        not null,
    speciality varchar(50)        not null
);


