package org.example.hospitalmanagement.persistence.model;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numberOfBeds;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clinic")
    private Set<Bed> beds;

    private String name;

    public int availableBeds() {
        if(beds.size()<numberOfBeds)
            return numberOfBeds-beds.size();
        else
            return (int) beds.stream().filter((bed)->bed.getPatient()==null).count();
    }

}
