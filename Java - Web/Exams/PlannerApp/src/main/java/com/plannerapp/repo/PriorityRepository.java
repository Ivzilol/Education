package com.plannerapp.repo;

import com.plannerapp.model.entity.entity.Priority;
import com.plannerapp.model.entity.enums.PriorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {
    @Query("select p from Priority as p" +
            " where p.priorityName = :priority")
    Priority findPriority(PriorityName priority);
}
