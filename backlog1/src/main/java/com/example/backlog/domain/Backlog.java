package com.example.backlog.domain;


import com.example.backlog.model.Project;
import com.example.backlog.model.ProjectTask;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "tbl_Backlogs")
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El identificador del proyecto no debe ser vacio")
    private String projectIdentifier;

    @Transient
    private Project project;

    @Transient
    private List<ProjectTask> projectTask;

    public Long getId() {
        return id;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public Project getProject() {
        return project;
    }

    public List<ProjectTask> getProjectTask() {
        return projectTask;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setProjectTask(List<ProjectTask> projectTask) {
        this.projectTask = projectTask;
    }
}
