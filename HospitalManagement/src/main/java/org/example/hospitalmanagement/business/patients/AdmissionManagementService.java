package org.example.hospitalmanagement.business.patients;

import org.example.hospitalmanagement.persistence.model.Admission;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.example.hospitalmanagement.persistence.model.Visit;
import org.example.hospitalmanagement.persistence.repositories.AdmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Admission> getAdmissionsByPatient(Long id) {
        Optional<List<Admission>> admissions = admissionRepository.findAdmissionsByPatientId(id);
        if(!admissions.isEmpty()){
            return admissions.get();
        }
        else {
            return null;
        }
    }
}
