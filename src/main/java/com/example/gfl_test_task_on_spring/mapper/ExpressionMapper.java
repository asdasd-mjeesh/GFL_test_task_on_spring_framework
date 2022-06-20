package com.example.gfl_test_task_on_spring.mapper;

import com.example.gfl_test_task_on_spring.dto.ExpressionDto;
import com.example.gfl_test_task_on_spring.entity.Expression;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpressionMapper implements Mapper<ExpressionDto, Expression> {

    @Override
    public ExpressionDto map(Expression from) {
        return new ExpressionDto(
                from.getId(),
                from.getValue(),
                from.getResult());
    }

    @Override
    public List<ExpressionDto> map(List<Expression> from) {
        return from.stream()
                .map(expression -> new ExpressionDto(
                        expression.getId(),
                        expression.getValue(),
                        expression.getResult()
                ))
                .collect(Collectors.toList());
    }
}
