package repository;

import model.TypeExamen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TypeExamenRepository extends JpaRepository<TypeExamen, Long> {
    List<TypeExamen> findByNameContainingIgnoreCase(String name);
}
