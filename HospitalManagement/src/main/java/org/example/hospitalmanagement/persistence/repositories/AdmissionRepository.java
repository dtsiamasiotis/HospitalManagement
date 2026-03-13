package org.example.hospitalmanagement.persistence.repositories;

import org.example.hospitalmanagement.persistence.model.Admission;
import org.example.hospitalmanagement.persistence.model.Patient;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface AdmissionRepository extends ListCrudRepository<Admission, Long> {
    List<Admission> findAll();

    Optional<List<Admission>> findAdmissionsByPatientId(long id);
    Optional<Admission> findAdmissionById(long id);
}
