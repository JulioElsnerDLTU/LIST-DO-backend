package com.jujo2021.dotasksproject.listmanagement.domain.services;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.DeleteTaskCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.UpdateTaskCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TaskCommandService {
    Long handle(CreateTaskCommand command); // Crea una nueva tarea
    void handle(DeleteTaskCommand command); // Elimina una tarea
    Optional<Task> handle(UpdateTaskCommand command); // Actualiza una tarea
}
