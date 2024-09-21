package org.example.hospitalmanagement.persistence.repositories;

import org.example.hospitalmanagement.persistence.model.Clinic;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ClinicRepository extends ListCrudRepository<Clinic, Long> {
    List<Clinic> findAll();
    Clinic findById(int id);
}
