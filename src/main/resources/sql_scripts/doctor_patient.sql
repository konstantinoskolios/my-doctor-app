CREATE TABLE IF NOT EXISTS doctor_patient
(
    id         serial PRIMARY KEY,
    doctor_id  BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,

    CONSTRAINT unique_doctor_patient_pair UNIQUE (doctor_id, patient_id)
);


