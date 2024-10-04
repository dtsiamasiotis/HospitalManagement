package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.hospitalmanagement.business.patients.Patient;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

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

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
