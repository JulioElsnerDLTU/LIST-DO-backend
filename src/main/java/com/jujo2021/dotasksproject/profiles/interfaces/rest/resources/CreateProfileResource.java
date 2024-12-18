package com.jujo2021.dotasksproject.profiles.interfaces.rest.resources;

public record CreateProfileResource(String username, String email, String imageUrl, String biography, String password) {
}
