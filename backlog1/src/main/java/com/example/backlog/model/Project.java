package com.example.backlog.model;

import com.example.backlog.domain.Backlog;
import lombok.Data;

import java.util.Date;

@Data
public class Project {
    private Long id;
    private String projectName;
    private String projectIdentifier;
    private String description;
    private Date startDate;
    private Date endDate;
    private Backlog backlog;
}
