package com.example.project.domain;

import com.example.project.model.Backlog;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "tbl_projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    @Column(unique = true)
    private String projectname;

    @Column(unique = true, updatable = false)
    @Size(min=5, max=7)
    private String Projectidentifier;

    @NotEmpty(message = "La descripcion no debe ser vacia")
    private String description;

    @Column(name = "start_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Transient
    private Backlog backlog;

    public Long getId() {
        return id;
    }

    public String getProjectname() {
        return projectname;
    }

    public String getProjectidentifier() {
        return Projectidentifier;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public void setProjectidentifier(String projectidentifier) {
        Projectidentifier = projectidentifier;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }
}
