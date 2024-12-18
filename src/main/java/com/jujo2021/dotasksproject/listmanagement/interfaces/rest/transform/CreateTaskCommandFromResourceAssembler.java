package com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform;

import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.CreateTaskResource;
import com.jujo2021.dotasksproject.profiles.domain.model.commands.CreateProfileCommand;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateTaskCommandFromResourceAssembler {
    public static CreateTaskCommand toCommandFromResource(CreateTaskResource resource) {
        return new CreateTaskCommand(resource.name(), resource.description(), resource.completed());
    }
}
