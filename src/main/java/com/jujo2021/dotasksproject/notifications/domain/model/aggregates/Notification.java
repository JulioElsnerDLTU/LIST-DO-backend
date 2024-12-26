package com.jujo2021.dotasksproject.notifications.domain.model.aggregates;
import com.jujo2021.dotasksproject.notifications.domain.model.commands.CreateNotificationCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String content;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private String timestamp;


    public Notification() {
    }

    public Notification(String name, String content, String timestamp) {
        this.name = name;
        this.content = content;
        this.status = false;
        this.timestamp = timestamp;
    }

    public void markAsRead() {
        this.status = true;
    }

    public Notification(CreateNotificationCommand command) {
        this.name = command.name();
        this.content = command.content();
        this.status = command.status();
        this.timestamp = command.timestamp();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getTimestamp() {
        return timestamp;
    }



}
