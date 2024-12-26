package com.jujo2021.dotasksproject.notifications.domain.model.commands;

public record CreateNotificationCommand(String name, String content,Boolean status,  String timestamp) {
}
