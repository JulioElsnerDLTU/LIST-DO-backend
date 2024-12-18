package com.jujo2021.dotasksproject.listmanagement.interfaces.rest;

import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.DeleteTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.DeleteTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.*;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskListCommandService;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskListQueryService;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.CreateTaskListResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.CreateTaskResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskListResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskResource;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform.TaskListResourceFromEntityAssembler;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.transform.TaskResourceFromEntityAssembler;
import com.jujo2021.dotasksproject.profiles.domain.services.ProfileCommandService;
import com.jujo2021.dotasksproject.profiles.domain.services.ProfileQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/api/v1/TaskList", produces = APPLICATION_JSON_VALUE)
@Tag(name = "TaskLists", description = "TaskLists Management Endpoints")
public class TaskListController {
    private final TaskListCommandService taskListCommandService;
    private final TaskListQueryService taskListQueryService;

    public TaskListController(TaskListCommandService taskListCommandService, TaskListQueryService taskListQueryService) {
        this.taskListCommandService = taskListCommandService;
        this.taskListQueryService = taskListQueryService;
    }

    /**
     * Create a new task List
     * @param createTaskListResource
     * @return
     */
    @PostMapping
    public Long createTask(CreateTaskListResource createTaskListResource) {
        return taskListCommandService.handle(new CreateTaskListCommand(createTaskListResource.title(), createTaskListResource.description()));
    }


    @GetMapping("/{taskListId}")
    public ResponseEntity<TaskListResource> getTaskListById(@PathVariable Long taskListId) {
        var getTaskListByIdQuery = new GetTaskListByIdQuery(taskListId);
        var task = taskListQueryService.handle(getTaskListByIdQuery);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskListResource = TaskListResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return ResponseEntity.ok(taskListResource);
    }

    /**
     * Get All Tasks
     * @param taskListId
     * @return
     */
    @GetMapping
    public ResponseEntity<TaskListResource> getAllTaskLists(@PathVariable Long taskListId) {
        var getTaskListByIdQuery = new GetTaskListByIdQuery(taskListId);
        var task = taskListQueryService.handle(getTaskListByIdQuery);
        if (task.isEmpty()) return ResponseEntity.badRequest().build();
        var taskListResource = TaskListResourceFromEntityAssembler.toResourceFromEntity(task.get());
        return ResponseEntity.ok(taskListResource);
    }


    /**
     * Get Task by Name
     * @param title
     * @return
     */
    @GetMapping
    public ResponseEntity<TaskListResource> findByTitle(@PathVariable String title) {
        var GetTaskListByTitleQuery = new GetTaskListByTitleQuery(title);
        var taskList = taskListQueryService.handle(GetTaskListByTitleQuery);
        if (taskList.isEmpty()) return ResponseEntity.badRequest().build();
        var taskListResource = TaskListResourceFromEntityAssembler.toResourceFromEntity(taskList.get()); //Optional
        return ResponseEntity.ok(taskListResource);
    }

    /**
     * Delete a Task
     * @param taskListId
     * @return
     */
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deletetaskList(@PathVariable Long taskListId) {
        var deletetaskListCommand = new DeleteTaskListCommand(taskListId);
        taskListCommandService.handle(deletetaskListCommand);
        return ResponseEntity.ok("Task List with given id successfully deleted");
    }
}
