package com.jujo2021.dotasksproject.listmanagement.application.queryservices;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetAllTaskListQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTaskListByIdQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTaskListByTitleQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.services.TaskListQueryService;
import com.jujo2021.dotasksproject.listmanagement.infrastructure.persistence.jpa.repositories.TaskListRepository;
import com.jujo2021.dotasksproject.listmanagement.interfaces.rest.resources.TaskListResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListQueryServiceImpl implements TaskListQueryService {

    private final TaskListRepository taskListRepository;

    public TaskListQueryServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> handle(GetAllTaskListQuery query) {
        return taskListRepository.findAll();
    }

    @Override
    public Optional<TaskList> handle(GetTaskListByIdQuery query) {
        return taskListRepository.findById(query.id());
    }

    @Override
    public Optional<TaskList> handle(GetTaskListByTitleQuery query) {
        return taskListRepository.findByTitle(query.title());
    }


}
