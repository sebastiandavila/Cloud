package com.example.project.service;

import com.example.project.domain.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getAllProjects();
    Project createProject(Project project);

}
