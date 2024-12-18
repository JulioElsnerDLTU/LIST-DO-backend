package com.jujo2021.dotasksproject.listmanagement.domain.model.commands;

public record CreateTaskCommand(String name, String description, Boolean completed) {
}
