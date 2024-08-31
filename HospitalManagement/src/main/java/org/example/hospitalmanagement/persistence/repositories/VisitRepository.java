package org.example.hospitalmanagement.persistence.repositories;

import org.example.hospitalmanagement.persistence.model.Visit;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface VisitRepository extends ListCrudRepository<Visit, Long> {
    Optional<List<Visit>> findVisitsByPatientId(long id);
}
