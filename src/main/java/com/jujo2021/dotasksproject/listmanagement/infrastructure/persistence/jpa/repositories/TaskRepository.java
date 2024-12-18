package com.jujo2021.dotasksproject.listmanagement.infrastructure.persistence.jpa.repositories;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    Optional<Task> findByName(String name);
    boolean existsByName(String name);

}
