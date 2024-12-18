package com.jujo2021.dotasksproject.listmanagement.infrastructure.persistence.jpa.repositories;

import com.jujo2021.dotasksproject.listmanagement.domain.model.aggregates.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    Optional<TaskList> findByTitle(String title);
    boolean existsByTitle(String title);
}
