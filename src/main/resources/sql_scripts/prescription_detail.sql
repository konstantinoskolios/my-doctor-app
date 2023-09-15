CREATE TABLE IF NOT EXISTS prescription_detail
(
    patient_id   bigint,
    doctor_id    bigint,
    category     varchar(50)  not null,
    prescription varchar(100) not null,
    date         varchar(10)  not null,
    CONSTRAINT unique_prescription_combo primary key (patient_id, doctor_id, prescription, date)
);
