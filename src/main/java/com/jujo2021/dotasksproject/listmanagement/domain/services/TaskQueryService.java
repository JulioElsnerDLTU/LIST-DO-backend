package com.jujo2021.dotasksproject.listmanagement.domain.services;
import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetAllTasksQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTaskByIdQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTasksByNameQuery;
import com.jujo2021.dotasksproject.listmanagement.domain.model.queries.GetTasksByTaskListIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskQueryService {
    List<Task> handle(GetAllTasksQuery query); // Obtiene todas las tareas
    Optional<Task> handle(GetTaskByIdQuery query); // Obtiene una tarea por su ID
    Optional<Task> handle(GetTasksByNameQuery query); // Obtiene tareas por su nombre
}
