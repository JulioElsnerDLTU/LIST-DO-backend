package com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskListResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskResource;

public class TaskResourceFromEntityAssembler {
    public static TaskResource toResourceFromEntity(Task entity) {
        return new TaskResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getCompleted());
    }
}
