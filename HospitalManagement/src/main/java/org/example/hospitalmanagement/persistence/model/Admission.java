package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hospitalmanagement.business.patients.Patient;

@Entity
@Data
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private AdmissionStatus status;

    private Long patientId;
    @ManyToOne
    @JoinColumn(name = "clinic_id", referencedColumnName = "id")
    private Clinic clinic;
}
