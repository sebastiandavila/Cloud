package com.example.project.service;

import com.example.project.domain.Project;
import com.example.project.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImplement implements ProjectService{

    private final ProjectRepository projectRepository;

    public ProjectServiceImplement(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {

        return projectRepository.save(project);
    }
}
