package com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform;

import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.CreateTaskListResource;

public class CreateTaskListCommandFromResourceAssembler {
    public static CreateTaskListCommand toCommandFromResource(CreateTaskListResource resource) {
        return new CreateTaskListCommand(resource.title(), resource.description());
    }
}
