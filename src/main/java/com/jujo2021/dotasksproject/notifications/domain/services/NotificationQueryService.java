package com.jujo2021.dotasksproject.notifications.domain.services;
import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import com.jujo2021.dotasksproject.notifications.domain.model.aggregates.Notification;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetNotificationByIdQuery;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetNotificationByNameQuery;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsQuery query); // Obtiene todas las notificaciones
    Optional<Notification> handle(GetNotificationByIdQuery query); // Obtiene una notificacion por su ID
    Optional<Notification> handle(GetNotificationByNameQuery query);
}
