package com.jujo2021.dotasksproject.listmanagement.interfaces.rest;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.DeleteTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetAllTasksQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTaskByIdQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTasksByNameQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskCommandService;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskQueryService;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.CreateTaskResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/v1/Task", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Tasks", description = "Tasks Management Endpoints")
public class TaskController {
    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    public TaskController(TaskCommandService taskCommandService, TaskQueryService taskQueryService) {
        this.taskCommandService = taskCommandService;
        this.taskQueryService = taskQueryService;
    }

    /**
     * Create a new task
     * @param createTaskResource
     * @return
     */
    @PostMapping
    public Long createTask(CreateTaskResource createTaskResource) {
        return taskCommandService.handle(new CreateTaskCommand(createTaskResource.name(), createTaskResource.description(), createTaskResource.completed()));
    }


    @GetMapping("/{taskId}")
    public ResponseEntity<TaskResource> getTaskById(@PathVariable Long taskId) {
        var getTaskByIdQuery = new GetTaskByIdQuery(taskId);
        var task = taskQueryService.handle(getTaskByIdQuery);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return ResponseEntity.ok(taskResource);
    }

    /**
     * Get All Tasks
     * @param
     * @return
     */
    @GetMapping
    public ResponseEntity<List<TaskResource>> getAllTasks() {
        var getAllTasksQuery = new GetAllTasksQuery();
        var tasks = taskQueryService.handle(getAllTasksQuery);
        var taskResources = tasks.stream()
                .map(TaskResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskResources);
    }


    /**
     * Get Task by Name
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<TaskResource> getTaskByName(@PathVariable String name) {
        var getTasksByNameQuery = new GetTasksByNameQuery(name);
        var task = taskQueryService.handle(getTasksByNameQuery);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskResource = TaskResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return ResponseEntity.ok(taskResource);
    }

    /**
     * Delete a Task
     * @param taskId
     * @return
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deletetask(@PathVariable Long taskId) {
        var deletetaskCommand = new DeleteTaskCommand(taskId);
        taskCommandService.handle(deletetaskCommand);
        return ResponseEntity.ok("Task with given id successfully deleted");
    }


}
