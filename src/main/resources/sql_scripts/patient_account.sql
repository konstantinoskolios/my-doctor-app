CREATE TABLE IF NOT EXISTS patientAccount
(
    id                   serial primary key,
    firstName            VARCHAR(50)        not null,
    lastName             varchar(50)        not null,
    fatherFirstName      varchar(50)        not null,
    taxNumber            varchar(9) UNIQUE  not null,
    socialSecurityNumber varchar(11) UNIQUE not null,
    phoneNumber          varchar(10) UNIQUE not null,
    birthdate            varchar(50)        not null,
    comments             varchar(255),
    prescriptionsIds     varchar(255)       not null,
    doctorId             varchar(50)        not null
);


