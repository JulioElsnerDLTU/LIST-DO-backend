package com.jujo2021.dotasksproject.notifications.application.queryservices;
import com.jujo2021.dotasksproject.notifications.domain.model.aggregates.Notification;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetNotificationByIdQuery;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetNotificationByNameQuery;
import com.jujo2021.dotasksproject.notifications.domain.services.NotificationQueryService;
import com.jujo2021.dotasksproject.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery query) {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.id());
    }

    @Override
    public Optional<Notification> handle(GetNotificationByNameQuery query) {
        return notificationRepository.findByName(query.name());
    }
}
