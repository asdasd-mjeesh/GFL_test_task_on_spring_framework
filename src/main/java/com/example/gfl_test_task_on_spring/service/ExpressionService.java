package com.example.gfl_test_task_on_spring.service;

import com.example.gfl_test_task_on_spring.domain.Calculator;
import com.example.gfl_test_task_on_spring.domain.ExpressionResolver;
import com.example.gfl_test_task_on_spring.dto.ExpressionFilter;
import com.example.gfl_test_task_on_spring.entity.Expression;
import com.example.gfl_test_task_on_spring.exception.InvalidExpressionException;
import com.example.gfl_test_task_on_spring.repository.ExpressionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpressionService {

    @Value("${page.size}")
    private Integer pageSize;
    private final Calculator calculator;
    private final ExpressionResolver expressionResolver;
    private final ExpressionRepository expressionRepository;

    @Autowired
    public ExpressionService(Calculator calculator,
                             ExpressionResolver expressionResolver,
                             ExpressionRepository expressionRepository) {
        this.calculator = calculator;
        this.expressionResolver = expressionResolver;
        this.expressionRepository = expressionRepository;
    }

    public Expression save(String expressionValue) {
        expressionValue = expressionValue.replace(" ", "");
        if (!expressionResolver.resolve(expressionValue)) {
            throw new InvalidExpressionException("invalid expression");
        } else {
            Double result = calculator.calculate(expressionValue);
            Expression expression = new Expression(expressionValue, result);
            return expressionRepository.save(expression);
        }
    }

    public Optional<Expression> findById(Long id) {
        return expressionRepository.findById(id);
    }

    public List<Expression> findAll() {
        return expressionRepository.findAll();
    }

    public List<Expression> findAllOnPage(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.unsorted());
        Page<Expression> queryResult = expressionRepository.findAll(pageable);

        if (queryResult.hasContent()) {
            return queryResult.getContent();
        }
        return new ArrayList<>();
    }

    public List<Expression> findAllByFilter(ExpressionFilter filter) {
        filter.setPageSize(pageSize);
        return expressionRepository.findAllByFilter(filter);
    }

    public void update(Expression expression) {
        if (!expressionResolver.resolve(expression.getValue())) {
            throw new InvalidExpressionException("invalid expression");
        }
        expression.setResult(calculator.calculate(expression.getValue()));
        expressionRepository.save(expression);
    }

    public void delete(Long id) {
        expressionRepository.deleteById(id);
    }
}
