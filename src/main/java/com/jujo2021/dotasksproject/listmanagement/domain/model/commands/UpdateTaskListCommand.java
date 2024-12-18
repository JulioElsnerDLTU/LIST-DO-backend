package com.jujo2021.dotasksproject.listmanagement.domain.model.commands;

public record UpdateTaskListCommand(Long id, String title, String description) {
}
