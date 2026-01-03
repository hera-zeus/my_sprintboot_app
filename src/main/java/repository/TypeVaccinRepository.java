package repository;

import model.TypeVaccin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TypeVaccinRepository extends JpaRepository<TypeVaccin, Long> {
    Optional<TypeVaccin> findByName(String name);
}
