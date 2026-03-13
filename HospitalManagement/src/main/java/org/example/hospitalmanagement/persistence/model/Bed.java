package org.example.hospitalmanagement.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Bed {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @JoinColumn(name="patient_id",referencedColumnName = "id")
    private Patient patient;
    @ManyToOne
    private Clinic clinic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bed )) return false;
        return id != null && id.equals(((Bed) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
