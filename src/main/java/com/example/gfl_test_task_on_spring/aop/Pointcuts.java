package com.example.gfl_test_task_on_spring.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* com.example.gfl_test_task_on_spring.service..*(..))")
    public void allServiceLayerMethods() {
    }
}
