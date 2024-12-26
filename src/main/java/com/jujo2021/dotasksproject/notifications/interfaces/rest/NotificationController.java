package com.jujo2021.dotasksproject.notifications.interfaces.rest;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTasksByNameQuery;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import com.jujo2021.dotasksproject.notifications.domain.model.commands.DeleteNotificationCommand;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetAllNotificationsQuery;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetNotificationByIdQuery;
import com.jujo2021.dotasksproject.notifications.domain.model.queries.GetNotificationByNameQuery;
import com.jujo2021.dotasksproject.notifications.domain.services.NotificationCommandService;
import com.jujo2021.dotasksproject.notifications.domain.services.NotificationQueryService;
import com.jujo2021.dotasksproject.notifications.interfaces.rest.resources.CreateNotificationResource;
import com.jujo2021.dotasksproject.notifications.interfaces.rest.resources.NotificationResource;
import com.jujo2021.dotasksproject.notifications.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.jujo2021.dotasksproject.notifications.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/notifications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notifications Management Endpoints")
public class NotificationController {

    private final NotificationQueryService notificationQueryService;
    private final NotificationCommandService notificationCommandService;

    public NotificationController(NotificationQueryService notificationQueryService, NotificationCommandService notificationCommandService) {
        this.notificationQueryService = notificationQueryService;
        this.notificationCommandService = notificationCommandService;
    }

    /**
     * Create a new notification
     * @param createNotificationResource
     * @return
     */
    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource createNotificationResource) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(createNotificationResource);
        var notificationId = notificationCommandService.handle(createNotificationCommand);
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) return ResponseEntity.badRequest().build();
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    /**
     * Get notification by id
     * @param notificationId
     * @return
     */
    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long notificationId) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) return ResponseEntity.badRequest().build();
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    /**
     * Get all notifications
     * @return
     */
    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var getAllNotificationsQuery = new GetAllNotificationsQuery();
        var notifications = notificationQueryService.handle(getAllNotificationsQuery);
        var notificationResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(notificationResources);
    }

    /**
     * Get notification by name
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<NotificationResource> getNotificationByName(@PathVariable String name) {
        var getNotificationsByNameQuery = new GetNotificationByNameQuery(name);
        var notification = notificationQueryService.handle(getNotificationsByNameQuery);
        if (notification.isEmpty()) return ResponseEntity.badRequest().build();
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    /**
     * Delete a notification
     * @param notificationId
     * @return
     */
    @DeleteMapping("/{notificationId}")
    public void deleteNotification(@PathVariable Long notificationId) {
        notificationCommandService.handle(new DeleteNotificationCommand(notificationId));
    }
}
