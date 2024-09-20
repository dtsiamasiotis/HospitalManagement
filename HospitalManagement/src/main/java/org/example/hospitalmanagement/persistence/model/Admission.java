package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private AdmissionStatus status;

    private Long patientId;

    private Long clinicId;
}
