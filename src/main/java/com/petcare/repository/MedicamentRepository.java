package com.petcare.repository;

import com.petcare.model.Medicament;
import com.petcare.model.FormeMedicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long> {

    List<Medicament> findByNameContainingIgnoreCase(String name);

    List<Medicament> findByMoleculeContainingIgnoreCase(String molecule);

    List<Medicament> findByShape(FormeMedicament shape);

    boolean existsByName(String name);
}
