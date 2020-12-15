package com.example.projecttask.domain;
import com.example.projecttask.model.Backlog;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;


@Entity
@Table(name = "tbl_projecttask")
public class ProjectTask {

    @Id
    @NotNull(message = "El ID no debe ser vacio")
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String name;

    @NotEmpty(message = "El resumen no debe ser vacio")
    private String summary;

    private String acceptanceCriteria;

    private String status;

    @Min(value = 1) @Max(value = 5)
    private int priority;

    @Min(value = 1) @Max(value = 8) @Positive
    private Double hours;

    @Column(name = "start_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(updatable = false)
    private String Projectidentifier;

    @Transient
    private Backlog backlog;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSummary() {
        return summary;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public String getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public Double getHours() {
        return hours;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getProjectidentifier() {
        return Projectidentifier;
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setAcceptanceCriteria(String acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setProjectidentifier(String projectidentifier) {
        Projectidentifier = projectidentifier;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }
}
