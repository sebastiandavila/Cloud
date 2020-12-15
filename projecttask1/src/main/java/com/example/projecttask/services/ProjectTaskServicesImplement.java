package com.example.projecttask.services;
import com.example.projecttask.domain.ProjectTask;
import com.example.projecttask.repository.ProjectTaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


@Service
public class ProjectTaskServicesImplement implements ProjectTaskServices{
    private final ProjectTaskRepository projectTaskRepository;

    public ProjectTaskServicesImplement(ProjectTaskRepository projectTaskRepository) {
        this.projectTaskRepository = projectTaskRepository;
    }

    @Override
    public List<ProjectTask> getAllTask() {
        return projectTaskRepository.findAll();
    }

    @Override
    public ProjectTask createProjectTask(ProjectTask projectTask) {
        List<ProjectTask> ProjectsTask = projectTaskRepository.findAll();
        Boolean Existe=false;

        for (int i=0; i<ProjectsTask.size(); i++) {

            if (projectTask.getStatus().equals("Not started") || projectTask.getStatus().equals("In progress") ||
                    projectTask.getStatus().equals("Completed") || projectTask.getStatus().equals("Deleted")) {

                if ((projectTask.getProjectidentifier().equals(ProjectsTask.get(i).getProjectidentifier()) &&
                        projectTask.getName().equals(ProjectsTask.get(i).getName()))) {
                    Existe=true;

                }
            }
            else{Existe=true;}
        }

            if(Existe)
            {return null;}
        else {
                return projectTaskRepository.save(projectTask);
            }
    }

   @Override
    public List<ProjectTask> findByProjectidentifier(String projectIdentifier) {
       List<ProjectTask> ProjectsTask = projectTaskRepository.findAll();
       List<ProjectTask> ProjectsTask1= new ArrayList<ProjectTask>();

       for (int i=0; i<ProjectsTask.size(); i++)
       {
           if(ProjectsTask.get(i).getProjectidentifier().equals(projectIdentifier))
           {
               ProjectsTask1.add(ProjectsTask.get(i));
           }
       }

        return ProjectsTask1;
    }

    @Override
    public Double getProjectHours(String projectIdentifier){
        Double Acumulador=0.0;
        List<ProjectTask> ProjectsTask = projectTaskRepository.findAll();

        for (int i=0; i<ProjectsTask.size(); i++)
        {
            if(!ProjectsTask.get(i).getStatus().equals("Deleted") && ProjectsTask.get(i).getProjectidentifier().equals(projectIdentifier))
            {
                Acumulador+=ProjectsTask.get(i).getHours();
            }
        }
        return Acumulador;
    }

    @Override
    public Double getProjectHoursStatus(String projectIdentifier, String status){
        Double Acumulador=0.0;
        List<ProjectTask> ProjectsTask = projectTaskRepository.findAll();

        for (int i=0; i<ProjectsTask.size(); i++)
        {
            if(ProjectsTask.get(i).getStatus().equals(status) && ProjectsTask.get(i).getProjectidentifier().equals(projectIdentifier))
            {
                Acumulador+=ProjectsTask.get(i).getHours();
            }
        }

        return Acumulador;
    }

    @Override
    public ProjectTask getProjectTask( Long id){
        return projectTaskRepository.findById(id).orElse(null);
    }

    @Override
    public  ProjectTask deleteProjectTask(Long id){
        ProjectTask projectTaskBD = this.getProjectTask(id);
        if(projectTaskBD==null) {
            return null;
        }
        projectTaskBD.setStatus("Deleted");
        return projectTaskRepository.save(projectTaskBD);
    }
}
