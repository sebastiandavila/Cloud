package com.example.project.model;

import lombok.Data;

import java.util.List;

@Data
public class Backlog {
    private Long id;
    private String projectIdentifier;
    private Project project;
    private List<ProjectTask> projectTask;
}
