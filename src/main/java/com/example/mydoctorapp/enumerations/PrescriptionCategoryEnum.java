package com.example.mydoctorapp.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrescriptionCategoryEnum {
    Cardiovascular(new String[]{
            "Echocardiogram",
            "Electrocardiogram (ECG or EKG)",
            "Stress Test",
            "Holter Monitor",
            "Cardiac Catheterization"
    }),

    Respiratory(new String[]{
            "Pulmonary Function Test (PFT)",
            "Chest X-ray",
            "CT Scan of the Chest",
            "Bronchoscopy",
            "Sputum Culture"
    }),

    Metabolic(new String[]{
            "Blood Glucose Test",
            "Cholesterol Panel",
            "Thyroid Function Test",
            "Hemoglobin A1c (HbA1c)",
            "Liver Function Test"
    }),
    Cancer(new String[]{"Mammogram", "Pap Smear", "Colonoscopy", "Prostate-Specific Antigen (PSA) Test", "Skin Cancer Screening"}),


    Gynecological(new String[]{"Pelvic Exam", "Breast MRI", "Transvaginal Ultrasound", "Prenatal Testing (Ultrasound)", "Hysteroscopy"}),

    Andrological(new String[]{"Testosterone Level Test", "Prostate Biopsy", "Seminal Analysis", "Male Hormone Profile", "Erectile Function Assessment"}),

    Pediatric(new String[]{"Pediatric Well-Child Visit", "Childhood Immunizations", "Developmental Screening", "Lead Screening", "Newborn Screening"}),

    Geriatric(new String[]{
            "Geriatric Assessment",
            "Osteoarthritis Evaluation",
            "Dementia Screening",
            "Frailty Assessment",
            "Polypharmacy Review"
    });

    private final String[] examTypes;
}
