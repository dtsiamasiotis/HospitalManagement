package org.example.hospitalmanagement.business.patients;

import org.example.hospitalmanagement.business.AdmissionFormData;
import org.example.hospitalmanagement.persistence.model.Admission;
import org.example.hospitalmanagement.persistence.repositories.AdmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionManagementService {
    private AdmissionRepository admissionRepository;

    public AdmissionManagementService(AdmissionRepository admissionRepository) {
        this.admissionRepository = admissionRepository;
    }

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public Admission addAdmission(Admission admission) {
        return admissionRepository.save(admission);
    }
}
