package com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates;

import com.jujo2021.dotasksproject.listmanagement.domain.model.commands.CreateTaskCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private Boolean completed;

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
    }

    public Task(CreateTaskCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.completed = command.completed();
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void updateTask(Long id, String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return completed;
    }


}
