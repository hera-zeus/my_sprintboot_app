package repository;

import model.HoraireTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface HoraireTravailRepository extends JpaRepository<HoraireTravail, Long> {

    List<HoraireTravail> findByVeterinaryIdOrderByDayAsc(Long veterinaryId);

    Optional<HoraireTravail> findByVeterinaryIdAndDay(Long veterinaryId, DayOfWeek day);
}
