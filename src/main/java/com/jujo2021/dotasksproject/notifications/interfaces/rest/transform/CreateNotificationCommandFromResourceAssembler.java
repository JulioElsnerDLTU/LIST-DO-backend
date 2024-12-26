package com.jujo2021.dotasksproject.notifications.interfaces.rest.transform;

import com.jujo2021.dotasksproject.notifications.domain.model.commands.CreateNotificationCommand;
import com.jujo2021.dotasksproject.notifications.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(resource.name(), resource.content(), resource.status(), resource.timestamp());
    }
}
