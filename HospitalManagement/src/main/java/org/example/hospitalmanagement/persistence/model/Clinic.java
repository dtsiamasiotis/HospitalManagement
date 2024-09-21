package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

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


}
