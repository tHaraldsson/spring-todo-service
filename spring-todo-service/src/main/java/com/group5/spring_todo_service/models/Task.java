package com.group5.spring_todo_service.models;

import com.group5.spring_todo_service.dto.TaskResponseDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "isComplete")
    private boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser user;

    public Task() {
    }
    public Task(String title, String description, boolean isComplete, CustomUser user) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public CustomUser getUser() {
        return user;
    }

    public void setUser(CustomUser user) {
        this.user = user;
    }

    public TaskResponseDTO toDTO() {

        return new TaskResponseDTO(
                id, title, description, isComplete, user.getEmail()
        );
    }
}
