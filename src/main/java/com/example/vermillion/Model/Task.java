package com.example.vermillion.Model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taskName;

    @Column(length = 50, nullable = false)
    private String status;

    private LocalDate dueDate;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assignedTo", nullable = false)
    private Developer assignedTo;

    public Task() {
    }

    public Task(Long id, String taskName, String status, LocalDate dueDate, String description, Project project, Developer assignedTo) {
        this.id = id;
        this.taskName = taskName;
        this.status = status;
        this.dueDate = dueDate;
        this.description = description;
        this.project = project;
        this.assignedTo = assignedTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Developer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Developer assignedTo) {
        this.assignedTo = assignedTo;
    }
}

