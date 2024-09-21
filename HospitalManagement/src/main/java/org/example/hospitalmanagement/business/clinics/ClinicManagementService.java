package org.example.hospitalmanagement.business.clinics;

import org.example.hospitalmanagement.persistence.model.Clinic;
import org.example.hospitalmanagement.persistence.model.Visit;
import org.example.hospitalmanagement.persistence.repositories.ClinicRepository;
import org.example.hospitalmanagement.persistence.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicManagementService {

    private ClinicRepository clinicRepository;

    public ClinicManagementService(ClinicRepository clinicRepository){
        this.clinicRepository = clinicRepository;
    }

    public List<Clinic> getAllClinics(){
       return clinicRepository.findAll();
    }

    public Optional<Clinic> getClinicById(long id){
        return clinicRepository.findById(id);
    }
}
