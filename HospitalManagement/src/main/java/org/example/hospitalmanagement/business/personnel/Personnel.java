package org.example.hospitalmanagement.business.personnel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@Data
public class Personnel {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String occupation;
    private String email;
    private Long clinicId;
    private Long baseSalary;
}
