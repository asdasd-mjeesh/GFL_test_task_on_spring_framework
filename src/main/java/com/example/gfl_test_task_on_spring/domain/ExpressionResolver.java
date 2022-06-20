package com.example.gfl_test_task_on_spring.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExpressionResolver {

    @Value("${math.signs}")
    private String mathSigns;

    public ExpressionResolver() {  }

    public boolean resolve(String expression) {
        return resolveBrackets(expression) && resolveOperations(expression) && resolveContext(expression);
    }

    private boolean resolveBrackets(String expression) {
        int openBrackets = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                openBrackets++;
            } else if(expression.charAt(i) == ')') {
                openBrackets--;
            }
        }
        return openBrackets == 0;
    }

    private boolean resolveOperations(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            char previousChar = '_';
            if (i > 0) {
                previousChar = expression.charAt(i - 1);
            }

            for (int j = 0; j < mathSigns.length(); j++) {
                if (currentChar == mathSigns.charAt(j)) {
                    if ((i < 1 && currentChar != '-') || i > expression.length() - 1) {
                        return false;
                    }

                    if (mathSigns.contains(String.valueOf(previousChar)) && previousChar != '*' && currentChar != '-'
                        && mathSigns.contains(String.valueOf(currentChar))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean resolveContext(String expression) {
        return true;
    }
}
