package com.example.backlog.service;

import com.example.backlog.domain.Backlog;

import java.util.List;

public interface BacklogService {
    Backlog createBacklog(Backlog backlog);
    List<Backlog> getAllbacklogs();
}
