package com.example.gfl_test_task_on_spring.exception;

public class ExpressionAlreadyExistException extends RuntimeException {

    public ExpressionAlreadyExistException(String message) {
        super(message);
    }
}
