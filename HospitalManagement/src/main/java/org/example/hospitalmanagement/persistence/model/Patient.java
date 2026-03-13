package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String email;
    @Column(unique = true, nullable = false)
    @Positive
    private Long insuranceNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Bed bed;
}
