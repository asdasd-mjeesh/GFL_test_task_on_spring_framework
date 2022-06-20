package com.example.gfl_test_task_on_spring.repository;

import com.example.gfl_test_task_on_spring.entity.Expression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressionRepository extends
        JpaRepository<Expression, Long>,
        CustomExpressionRepository {

}
