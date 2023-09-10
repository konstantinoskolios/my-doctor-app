CREATE TABLE IF NOT EXISTS patient_account
(
    id                     BIGINT,
    first_name             VARCHAR(50)        not null,
    last_name              varchar(50)        not null,
    father_first_name      varchar(50)        not null,
    tax_number             varchar(9) UNIQUE  not null,
    social_security_number varchar(11) UNIQUE not null,
    phone_number           varchar(10) UNIQUE not null,
    birthdate              varchar(50)        not null,
    comments               varchar(255),
    prescriptions_ids      varchar(255),
    doctor_id              BIGINT             not null,
    PRIMARY KEY (id, doctor_id)
);
