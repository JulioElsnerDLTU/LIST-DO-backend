package com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskListResource;
import com.jujo2021.dotasksproject.profiles.domain.model.aggregates.Profile;
import com.jujo2021.dotasksproject.profiles.interfaces.rest.resources.ProfileResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;



public class TaskListResourceFromEntityAssembler {
    public static TaskListResource toResourceFromEntity(TaskList entity) {
        return new TaskListResource(entity.getId(), entity.getTitle(), entity.getDescription());
    }
}
