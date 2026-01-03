package repository;

import model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByReceiverIdOrderByCreateDateDesc(Long userId);

    long countByReceiverIdAndReadFalse(Long userId);

    List<Notification> findByScheduledSendDateBeforeAndReadFalse(LocalDateTime now);
}
