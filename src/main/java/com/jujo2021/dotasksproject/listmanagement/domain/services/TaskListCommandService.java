package com.jujo2021.dotasksproject.listmanagement.domain.services;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.DeleteTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.UpdateTaskListCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TaskListCommandService {
    Long handle(CreateTaskListCommand command); // Crea una nueva lista de tareas
    void handle(DeleteTaskListCommand command); // Elimina una lista de tareas
    Optional<TaskList> handle(UpdateTaskListCommand command);
}
