package com.example.projecttask.controller;

import com.example.projecttask.domain.ProjectTask;
import com.example.projecttask.model.ErrorMessage;

import com.example.projecttask.services.ProjectTaskServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.logging.*;

@Slf4j
@RestController
@RequestMapping(value = "/task")
public class ProjectTaskController {

    private final ProjectTaskServices projectTaskServices;

    public ProjectTaskController(ProjectTaskServices projectTaskServices){
        this.projectTaskServices = projectTaskServices;
    }

    @PostMapping
    public ResponseEntity<ProjectTask> createProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        ProjectTask projectBD = projectTaskServices.createProjectTask(projectTask);
        if (projectBD==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(projectBD);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(projectBD);
    }

    @GetMapping
    public ResponseEntity<List<ProjectTask>> getTasks(){

        List<ProjectTask> task = projectTaskServices.getAllTask();
        if(task.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping(value = "/project/{ProjectIdentifier}")
    public ResponseEntity<List<ProjectTask>> getTasksProject(@PathVariable("ProjectIdentifier") String ProjectIdentifier){

        List<ProjectTask> task = projectTaskServices.findByProjectidentifier(ProjectIdentifier);
        if(task.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @GetMapping(value = "/hours/project/{ProjectIdentifier}")
    public ResponseEntity<Double> getProjectHours(@PathVariable("ProjectIdentifier") String ProjectIdentifier){

        List<ProjectTask> task = projectTaskServices.getAllTask();
        if(task.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projectTaskServices.getProjectHours(ProjectIdentifier));
    }

    @GetMapping(value = "/hours/project/{ProjectIdentifier}/{status}")
    public ResponseEntity<Double> getProjectHoursStatus(@PathVariable("ProjectIdentifier") String ProjectIdentifier, @PathVariable("status") String status){

        List<ProjectTask> task = projectTaskServices.getAllTask();
        if(task.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(projectTaskServices.getProjectHoursStatus(ProjectIdentifier, status));
    }

    @DeleteMapping(value = "/{idTask}")
    public ResponseEntity<ProjectTask> deleteProjectTask(@PathVariable("idTask") long idTask) {
        log.info("Fetching & Deleting Invoice with id {}", idTask);

        ProjectTask projectTask = projectTaskServices.getProjectTask(idTask);
        if (projectTask == null) {
            log.error("Unable to delete. Invoice with id {} not found.", idTask);
            return  ResponseEntity.notFound().build();
        }
        projectTask = projectTaskServices.deleteProjectTask(idTask);
        return ResponseEntity.ok(projectTask);
    }

    private String formatMessage(BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(),err.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return json;
    }
}
