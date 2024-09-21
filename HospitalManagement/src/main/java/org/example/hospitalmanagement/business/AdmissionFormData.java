package org.example.hospitalmanagement.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdmissionFormData {
    private Long clinicId;
    private Long patientId;
}
