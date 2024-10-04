package org.example.hospitalmanagement.persistence.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class AdmissionFormData {
    private Long clinicId;
    private Long patientId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime endDate;
}
