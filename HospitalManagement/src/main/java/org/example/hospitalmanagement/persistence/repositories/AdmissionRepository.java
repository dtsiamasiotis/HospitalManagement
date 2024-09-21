package org.example.hospitalmanagement.persistence.repositories;

import org.example.hospitalmanagement.persistence.model.Admission;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface AdmissionRepository extends ListCrudRepository<Admission, Long> {
    List<Admission> findAll();
}
