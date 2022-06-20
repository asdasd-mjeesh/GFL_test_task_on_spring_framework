package com.example.gfl_test_task_on_spring.dto;

import lombok.Data;

@Data
public final class ExpressionFilter {
    private final Double equals;
    private final Double min;
    private final Double max;
    private int currentPage;
    private int pageSize;

    public ExpressionFilter(Double equals, Double min, Double max, int currentPage) {
        this.equals = equals;
        this.min = min;
        this.max = max;
        this.currentPage = currentPage;
    }
}
