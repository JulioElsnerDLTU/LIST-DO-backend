package com.jujo2021.dotasksproject.listmanagement.application.commandservices;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.DeleteTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.UpdateTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskCommandService;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskListCommandService;
import com.jujo2021.dotasksproject.listmanagement.infrastructure.persistence.jpa.repositories.TaskListRepository;
import com.jujo2021.dotasksproject.profiles.domain.model.aggregates.Profile;

import java.util.Optional;

public class TaskListCommandServiceImpl implements TaskListCommandService {

    private final TaskListRepository taskListRepository;

    public TaskListCommandServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }


    @Override
    public Long handle(CreateTaskListCommand command) {
        if (taskListRepository.existsByTitle(command.title())){
            throw new IllegalArgumentException("TaskList with same title already exists");
        }
        var taskList = new TaskList(command);
        try {
            taskListRepository.save(taskList);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving taskList: " + e.getMessage());
        }
        return taskList.getId();
    }

    @Override
    public void handle(DeleteTaskListCommand command) {
        if (!taskListRepository.existsById(command.id())) {
            throw new IllegalArgumentException("TaskList does not exist");
        }
        try {
            taskListRepository.deleteById(command.id());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting TaskList: " + e.getMessage());
        }

    }

    @Override
    public Optional<TaskList> handle(UpdateTaskListCommand command) {
        var taskList = taskListRepository.findById(command.id());
        if (taskList.isEmpty()) {
            throw new IllegalArgumentException("TaskList does not exist");
        }
        var taskListToUpdate = taskList.get();
        taskListToUpdate.update(command);
        try {
            taskListRepository.save(taskListToUpdate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating TaskList: " + e.getMessage());
        }
        return Optional.of(taskListToUpdate);
    }
}
