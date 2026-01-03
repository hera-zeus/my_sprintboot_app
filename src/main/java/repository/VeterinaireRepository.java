package repository;

import model.Veterinaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VeterinaireRepository extends JpaRepository<Veterinaire, Long> {

    List<Veterinaire> findBySpecialityName(String name);

    Optional<Veterinaire> findBylicenseNumber(String licenseNumber);

    List<Veterinaire> findByClinicName(String clinicName);
}