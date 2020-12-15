package com.example.backlog.service;

import com.example.backlog.domain.Backlog;
import com.example.backlog.repository.BacklogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacklogServiceImplement implements BacklogService{

    private final BacklogRepository backlogRepository;

    public BacklogServiceImplement(BacklogRepository backlogRepository) {
        this.backlogRepository = backlogRepository;
    }

    @Override
    public Backlog createBacklog(Backlog backlog) {
        return backlogRepository.save(backlog);
    }

    @Override
    public List<Backlog> getAllbacklogs() {
        return backlogRepository.findAll();
    }

}
