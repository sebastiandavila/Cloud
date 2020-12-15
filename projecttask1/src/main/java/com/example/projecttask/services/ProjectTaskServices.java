package com.example.projecttask.services;

import com.example.projecttask.domain.ProjectTask;

import java.util.List;

public interface ProjectTaskServices {

    ProjectTask createProjectTask(ProjectTask projectTask);
    List<ProjectTask> findByProjectidentifier(String projectIdentifier);
    Double getProjectHours(String projectIdentifier);
    Double getProjectHoursStatus(String projectIdentifier, String status);
    ProjectTask getProjectTask( Long id);
    ProjectTask deleteProjectTask(Long id);
    List<ProjectTask> getAllTask();
}
