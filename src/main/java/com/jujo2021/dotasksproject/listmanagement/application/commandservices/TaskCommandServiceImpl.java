package com.jujo2021.dotasksproject.listmanagement.application.commandservices;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.DeleteTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.UpdateTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskCommandService;
import com.jujo2021.dotasksproject.listmanagement.infrastructure.persistence.jpa.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;

    public TaskCommandServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long handle(CreateTaskCommand command) {
        if (taskRepository.existsByName(command.name())){
            throw new IllegalArgumentException("Task with same name already exists");
        }
        var task = new Task(command);
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving taskList: " + e.getMessage());
        }
        return task.getId();
    }

    @Override
    public void handle(DeleteTaskCommand command) {
        if (!taskRepository.existsById(command.id())) {
            throw new IllegalArgumentException("Task does not exist");
        }
        try {
            taskRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting Task: " + e.getMessage());
        }
    }

    @Override
    public Optional<Task> handle(UpdateTaskCommand command) {
        var taskList = taskRepository.findById(command.id());
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException("TaskList does not exist");
        }
        var taskListToUpdate = taskList.get();
        taskListToUpdate.updateTask(command.id(), command.name(), command.description());
        try {
            taskRepository.save(taskListToUpdate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating TaskList: " + e.getMessage());
        }
        return Optional.of(taskListToUpdate);
    }
}
