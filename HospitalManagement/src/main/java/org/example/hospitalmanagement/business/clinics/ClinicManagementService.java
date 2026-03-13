package org.example.hospitalmanagement.business.clinics;

import jakarta.annotation.PostConstruct;
import org.example.hospitalmanagement.persistence.model.Clinic;
import org.example.hospitalmanagement.persistence.model.Visit;
import org.example.hospitalmanagement.persistence.repositories.ClinicRepository;
import org.example.hospitalmanagement.persistence.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Integer getAvailableNumberOfBeds(Clinic clinic) {
        return clinic.getBeds().size();
    }

    public List<Clinic> getClinicsWithAvailableBeds(){
        return clinicRepository.findAll().stream().filter((clinic)->clinic.availableBeds()>0).collect(Collectors.toList());
    }
}
