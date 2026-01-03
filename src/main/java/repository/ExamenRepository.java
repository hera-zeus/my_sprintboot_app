package repository;

import model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findByConsultationId(Long consultationId);

    List<Examen> findByExecuteFalse();

    List<Examen> findByTypeExamName(String typeName);
}
