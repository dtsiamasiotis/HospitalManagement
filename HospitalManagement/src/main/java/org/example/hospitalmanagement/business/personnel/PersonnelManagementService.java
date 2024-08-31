package org.example.hospitalmanagement.business.personnel;

import org.example.hospitalmanagement.business.patients.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonnelManagementService {

    private RestTemplate restTemplate;

    public PersonnelManagementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Personnel> getAllPersonnel() {
        ResponseEntity<Personnel[]> response = restTemplate.getForEntity("http://localhost:8080/listPersonnel", Personnel[].class);
        return Arrays.stream(response.getBody()).toList();
    }

    public Personnel getPersonnelById(Long id) {
        return restTemplate.getForObject("http://localhost:8080/getPersonnelById?id=" + id, Personnel.class);
    }

    @Scheduled(cron="*/10 * * * * *")
    public void calculateMonthlyCompensation() {
        restTemplate.getForObject("http://localhost:8080/calculateMonthlyCompensation", String.class);
    }
}
