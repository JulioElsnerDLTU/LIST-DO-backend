package com.jujo2021.dotasksproject.listmanagement.application.queryservices;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetAllTasksQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTaskByIdQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTasksByNameQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTasksByTaskListIdQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskQueryService;
import com.jujo2021.dotasksproject.listmanagement.infrastructure.persistence.jpa.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

public class TaskQueryServiceImpl implements TaskQueryService {

    private final TaskRepository taskRepository;

    public TaskQueryServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public List<Task> handle(GetAllTasksQuery query) {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> handle(GetTaskByIdQuery query) {
        return taskRepository.findById(query.id());
    }

    @Override
    public List<Task> handle(GetTasksByTaskListIdQuery query) {
        return taskRepository.findByTaskListId(query.taskListId());
    }

    @Override
    public Optional<Task> handle(GetTasksByNameQuery query) {
        return taskRepository.findByName(query.name());
    }
}
