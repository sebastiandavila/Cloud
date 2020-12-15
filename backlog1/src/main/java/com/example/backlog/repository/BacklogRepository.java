package com.example.backlog.repository;

import com.example.backlog.domain.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {

}
