package com.jujo2021.dotasksproject.profiles.domain.model.commands;

public record CreateProfileCommand(String username,String email, String imageUrl, String biography, String password) {
}
