package com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates;

import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskListCommand;
import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.UpdateTaskListCommand;
import com.jujo2021.dotasksproject.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class TaskList extends AuditableAbstractAggregateRoot<TaskList> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "tasklist_id")
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public TaskList(CreateTaskListCommand command) {
        this.title = command.title();
        this.description = command.description();
    }

    public TaskList updateTaskList(UpdateTaskListCommand command) {
        this.title = command.title();
        this.description = command.description();
        return this;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public void update(UpdateTaskListCommand command) {
        this.id = command.id();
        this.title = command.title();
        this.description = command.description();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
