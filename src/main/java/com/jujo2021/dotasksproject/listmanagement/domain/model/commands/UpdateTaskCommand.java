package com.jujo2021.dotasksproject.listmanagement.domain.model.commands;

public record UpdateTaskCommand(Long id, String name, String description) {
}
