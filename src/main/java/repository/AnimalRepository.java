package repository;

import model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Trouver tous les animaux d'un propriétaire spécifique
    List<Animal> findByOwnerId(Long ownerId);

    // Trouver un animal par son nom (ignorer la casse)
    List<Animal> findByNameContainingIgnoreCase(String name);

    // Trouver les animaux d'une certaine espèce
    List<Animal> findByEspeceId(Long especeId);
}
