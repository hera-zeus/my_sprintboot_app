package repository;

import model.Proprietaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProprietaireRepository extends JpaRepository<Proprietaire, Long> {
    // Trouver un propriétaire par son numéro de téléphone
    Optional<Proprietaire> findByPhone(String phone);
}
