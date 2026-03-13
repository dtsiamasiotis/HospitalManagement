package org.example.hospitalmanagement.business.patients;

import org.example.hospitalmanagement.business.RestTemplateResponseErrorHandler;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.example.hospitalmanagement.persistence.repositories.PatientRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PatientManagementService {

    private RestTemplate restTemplate;
    private PatientRepository patientRepository;

    @Autowired
    public PatientManagementService (RestTemplateBuilder restTemplateBuilder, PatientRepository patientRepository) {
        this.restTemplate = restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
        this.patientRepository = patientRepository;
    }

    public List<Patient> getAllPatients() {
           //ResponseEntity<Patient[]> response = restTemplate.getForEntity("http://localhost:8080/listPatients", Patient[].class);
           //return Arrays.stream(response.getBody()).toList();
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        //return restTemplate.getForObject("http://localhost:8080/getPatientById?id=" + id, Patient.class);
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.orElse(null);
    }

    public void createNewPatient(Patient patient) throws DataIntegrityViolationException {
        //restTemplate.postForObject("http://localhost:8080/createPatient", patient, PatientFromServer.class);

                patientRepository.save(patient);


    }
    
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    public PatientFromServer getPatientByInsuranceNumber(String insuranceNumber) {
        //return restTemplate.getForObject("http://localhost:8080/getPatientByInsuranceNumber?insuranceNumber=" + insuranceNumber, Optional<PatientFromServer.class>);
        ResponseEntity<PatientFromServer> resp = restTemplate.getForEntity("http://localhost:8080/getPatientByInsuranceNumber?insuranceNumber=" + insuranceNumber, PatientFromServer.class);
        return resp.getBody();
    }

    public Patient transformPatientFromServertoPatient(PatientFromServer patientFromServer) {
        Patient patient = new Patient();
        patient.setInsuranceNumber(patientFromServer.getInsuranceNumber());
        patient.setEmail(patientFromServer.getEmail());
        patient.setFirstName(patientFromServer.getFirstName());
        patient.setLastName(patientFromServer.getLastName());
        patient.setDateOfBirth(patientFromServer.getDateOfBirth());
        return patient;
    }
}
