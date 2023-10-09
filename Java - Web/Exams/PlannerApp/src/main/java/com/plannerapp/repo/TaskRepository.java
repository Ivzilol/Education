package com.plannerapp.repo;

import com.plannerapp.model.entity.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task as t" +
            " where t.id = :id")
    Set<Task> findTaskById(Long id);
}
