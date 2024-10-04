package org.example.hospitalmanagement.business.patients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@Data
public class Patient {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @Positive
    private Long insuranceNumber;
}
