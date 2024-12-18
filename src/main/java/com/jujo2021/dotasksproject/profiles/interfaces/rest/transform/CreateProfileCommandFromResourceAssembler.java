package com.jujo2021.dotasksproject.profiles.interfaces.rest.transform;

import com.jujo2021.dotasksproject.profiles.domain.model.commands.CreateProfileCommand;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(resource.username(), resource.email(), resource.biography(), resource.imageUrl(), resource.password());
    }
}
