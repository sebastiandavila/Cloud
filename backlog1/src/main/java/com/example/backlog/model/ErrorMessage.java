package com.example.backlog.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.List;
import java.util.Map;

@Getter @Setter @Builder @AllArgsConstructor
public class ErrorMessage {

    private String code;
    private List<Map<String,String>> messages;

}