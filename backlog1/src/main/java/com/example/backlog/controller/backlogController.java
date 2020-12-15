package com.example.backlog.controller;

import com.example.backlog.domain.Backlog;
import com.example.backlog.model.ErrorMessage;
import com.example.backlog.service.BacklogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
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

@RestController
@RequestMapping(value = "/backlogs")
public class backlogController {

    private final BacklogService backlogService;

    public backlogController(BacklogService backlogService){
        this.backlogService = backlogService;
    }

    @GetMapping
    public ResponseEntity<List<Backlog>> getBacklog(){

        List<Backlog> backlog;

            backlog = backlogService.getAllbacklogs();
            if(backlog.isEmpty()){
                return ResponseEntity.noContent().build();
            }

        return ResponseEntity.ok(backlog);
    }

    @PostMapping
    public ResponseEntity<Backlog> createProduct(@Valid @RequestBody Backlog backlog, BindingResult result){
        if(result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
        }
        Backlog productBD = backlogService.createBacklog(backlog);
        return ResponseEntity.status(HttpStatus.CREATED).body(productBD);
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
