package org.example.hospitalmanagement.business.patients;

import org.example.hospitalmanagement.persistence.model.Visit;
import org.example.hospitalmanagement.persistence.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitManagementService {

    private VisitRepository visitRepository;

    public VisitManagementService(VisitRepository visitRepository){
        this.visitRepository = visitRepository;
    }

    public List<Visit> getVisitsByPatientId(long id){
        Optional<List<Visit>> visits = visitRepository.findVisitsByPatientId(id);
        if(!visits.isEmpty()){
            return visits.get();
        }
        else {
            return null;
        }
    }
}
