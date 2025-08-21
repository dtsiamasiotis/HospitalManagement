package org.example.hospitalmanagement.persistence.repositories;

import org.example.hospitalmanagement.persistence.model.Admission;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PatientRepository extends ListCrudRepository<Patient, Long> {
    List<Patient> findAll();
    Patient findById(long id);
}
