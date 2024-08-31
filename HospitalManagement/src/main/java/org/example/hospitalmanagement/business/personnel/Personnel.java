package org.example.hospitalmanagement.business.personnel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(
        ignoreUnknown = true
)
@Data
public class Personnel {
    private Long id;
    private Long clinicId;
    private String occupation;
    private Long baseSalary;
}
