package com.example.gfl_test_task_on_spring.exception_handling;

import com.example.gfl_test_task_on_spring.exception.ExpressionAlreadyExistException;
import com.example.gfl_test_task_on_spring.exception.InvalidExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExpressionGlobalExceptionHandler {

    @ExceptionHandler(value = { ExpressionAlreadyExistException.class, InvalidExpressionException.class } )
    public ResponseEntity<ExceptionInfo> invalidExpressionHandler(Exception exception) {
        ExceptionInfo info = new ExceptionInfo(exception.getMessage());
        return new ResponseEntity<>(info, HttpStatus.CONFLICT);
    }
}
