package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bed {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private Long patientId;
    @ManyToOne
    private Clinic clinic;
}
