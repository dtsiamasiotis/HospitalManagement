package org.example.hospitalmanagement.business.patients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PatientManagementService {

    private RestTemplate restTemplate;

    public PatientManagementService (RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Patient> getAllPatients() {
       ResponseEntity<Patient[]> response = restTemplate.getForEntity("http://localhost:8080/listPatients", Patient[].class);
       return Arrays.stream(response.getBody()).toList();
    }

    public Patient getPatientById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/getPatientById?id=" + id, Patient.class);
    }

    public void addPatient(Patient patient) {
        restTemplate.postForObject("http://localhost:8080/createPatient", patient, Patient.class);
    }
}
