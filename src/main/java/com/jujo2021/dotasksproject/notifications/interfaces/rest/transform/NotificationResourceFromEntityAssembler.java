package com.jujo2021.dotasksproject.notifications.interfaces.rest.transform;

import com.jujo2021.dotasksproject.notifications.domain.model.aggregates.Notification;
import com.jujo2021.dotasksproject.notifications.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(entity.getId(), entity.getName(), entity.getContent(), entity.getStatus(), entity.getTimestamp());
    }
}
