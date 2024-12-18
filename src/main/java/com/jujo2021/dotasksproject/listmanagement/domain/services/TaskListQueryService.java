package com.jujo2021.dotasksproject.listmanagement.domain.services;
import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetAllTaskListQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetAllTasksQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTaskListByIdQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTaskListByTitleQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskListQueryService {
    List<TaskList> handle(GetAllTaskListQuery query); // Obtiene todas las listas de tareas
    Optional<TaskList> handle(GetTaskListByIdQuery query); // Obtiene una lista de tareas por su ID
    Optional<TaskList> handle(GetTaskListByTitleQuery query); // Obtiene listas de tareas por su título
}
