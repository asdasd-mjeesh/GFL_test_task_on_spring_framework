package com.example.gfl_test_task_on_spring.repository;

import com.example.gfl_test_task_on_spring.dto.ExpressionFilter;
import com.example.gfl_test_task_on_spring.entity.Expression;

import java.util.List;

public interface CustomExpressionRepository {

    List<Expression> findAllByFilter(ExpressionFilter filter);
}
