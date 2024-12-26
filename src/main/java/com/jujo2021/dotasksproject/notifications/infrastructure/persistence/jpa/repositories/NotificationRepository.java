package com.jujo2021.dotasksproject.notifications.infrastructure.persistence.jpa.repositories;
import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import com.jujo2021.dotasksproject.notifications.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    Optional<Notification> findById(Long id);
    Optional<Notification> findByName(String name);
    boolean existsByName(String name);
}
