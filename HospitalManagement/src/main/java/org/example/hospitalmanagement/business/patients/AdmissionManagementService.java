package org.example.hospitalmanagement.business.patients;

import org.example.hospitalmanagement.business.clinics.ClinicManagementService;
import org.example.hospitalmanagement.persistence.model.*;
import org.example.hospitalmanagement.persistence.repositories.AdmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdmissionManagementService {
    private AdmissionRepository admissionRepository;
    private ClinicManagementService clinicManagementService;

    public AdmissionManagementService(AdmissionRepository admissionRepository, ClinicManagementService clinicManagementService) {
        this.admissionRepository = admissionRepository;
        this.clinicManagementService = clinicManagementService;
    }

    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    public Admission addAdmission(Admission admission) {
        Clinic clinic = admission.getClinic();

        if (clinic.getBeds().size() < clinic.getNumberOfBeds()) {
            Bed bed = new Bed();
            bed.setPatient(admission.getPatient());
            clinic.getBeds().add(bed);
            bed.setClinic(clinic);
            admission.getPatient().setBed(bed);
        }else {
            Bed bed = clinic.getBeds().stream().filter((b)->b.getPatient()==null).findFirst().get();
            bed.setPatient(admission.getPatient());
            admission.getPatient().setBed(bed);
        }



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

    public void updateAdmission(Admission admission) {
        admissionRepository.save(admission);
    }

    public Admission getAdmissionById(Long id) {
        Optional<Admission> admission = admissionRepository.findAdmissionById(id);
        return admission.orElse(null);
    }

    public void completeAdmission(Admission admission) {

    }
}
