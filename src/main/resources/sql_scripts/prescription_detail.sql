CREATE TABLE IF NOT EXISTS prescription_detail
(
    id serial primary key,
    patient_id        bigint,
    doctor_id         bigint,
    category          varchar(50)  not null,
    prescription_name varchar(100) not null,
    date              varchar(10)  not null
)
