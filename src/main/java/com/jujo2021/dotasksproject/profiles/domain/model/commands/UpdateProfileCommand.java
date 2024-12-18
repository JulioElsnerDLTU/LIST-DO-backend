package com.jujo2021.dotasksproject.profiles.domain.model.commands;

public record UpdateProfileCommand(Long id, String biography, String imageUrl) {
}
