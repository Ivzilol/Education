package com.resellerapp.repository;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.enums.ConditionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<Condition, Long> {
    @Query("select c from Condition as c" +
            " where c.conditionName = :conditionName")
    Condition findCondition(ConditionName conditionName);
}
