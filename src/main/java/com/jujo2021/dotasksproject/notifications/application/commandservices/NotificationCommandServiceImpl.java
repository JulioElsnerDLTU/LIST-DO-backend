package com.jujo2021.dotasksproject.notifications.application.commandservices;
import com.jujo2021.dotasksproject.notifications.domain.model.aggregates.Notification;
import com.jujo2021.dotasksproject.notifications.domain.model.commands.CreateNotificationCommand;
import com.jujo2021.dotasksproject.notifications.domain.model.commands.DeleteNotificationCommand;
import com.jujo2021.dotasksproject.notifications.domain.services.NotificationCommandService;
import com.jujo2021.dotasksproject.notifications.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Long handle(CreateNotificationCommand command) {
        if (notificationRepository.existsByName(command.name())){
            throw new IllegalArgumentException("Notification with same name already exists");
        }
        var notification = new Notification(command);
        try {
            notificationRepository.save(notification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving notification: " + e.getMessage());
        }
        return notification.getId();
    }

    @Override
    public Void handle(DeleteNotificationCommand command) {
        if (!notificationRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Notification does not exist");
        }
        try {
            notificationRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Notification: " + e.getMessage());
        }
        return null;
    }
}
