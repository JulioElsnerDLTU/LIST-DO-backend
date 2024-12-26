package com.jujo2021.dotasksproject.notifications.domain.services;

import com.jujo2021.dotasksproject.notifications.domain.model.commands.CreateNotificationCommand;
import com.jujo2021.dotasksproject.notifications.domain.model.commands.DeleteNotificationCommand;
import org.springframework.stereotype.Service;

@Service
public interface NotificationCommandService {
    Long handle(CreateNotificationCommand command);
    Void handle(DeleteNotificationCommand command);
}
