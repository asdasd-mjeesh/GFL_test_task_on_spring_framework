package com.example.gfl_test_task_on_spring.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@ActiveProfiles("test")
class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    void calculate() {
        String expression = "4*-7";
        double result = calculator.calculate(expression);
        assertEquals(-28.0, result);
        System.out.println(result);

        expression = "4*-7*-5";
        result = calculator.calculate(expression);
        assertEquals(140, result);
        System.out.println(result);
    }

    @Test
    void simpleExpressionWithBrackets() {
        Double res = (25-16.5) + (300+200-400.2) + 2; // = 110.3
        res = calculator.round(res);

        String expression = "(25-16.5)+(300+200-400.2)+2";
        double result = calculator.calculate(expression);

        assertEquals(res, result);
        System.out.println(result);
    }

    @Test
    void calculateExpressionWithFloatPoints() {
        Double res = 29.6 + 16.13 * (9.5 / 6.8); // = 52.135
        res = calculator.round(res);

        String expression = "29.6+16.13*(9.5/6.8)";
        double result = calculator.calculate(expression);

        assertEquals(res, result);
        System.out.println(result);
    }

    @Test
    void testCalculationWhereFirstDigitHaveMinusSign() {
        Double res = -5.0 + 5.0;

        String expression = "-5+5";
        double result = calculator.calculate(expression);

        assertEquals(res, result);
        System.out.println(result);
    }

    @Test
    void testSumWhereSecondValueHaveMinusSign() {
        Double res = 5.0 + (- 5.0);

        String expression = "5+-5";
        double result = calculator.calculate(expression);

        assertEquals(res, result);
        System.out.println(result);
    }

    @Test
    void testMinusAfterOtherOperations() {
        Double res = 10.0 * 2.0 - 5.0; // = 20
        res = calculator.round(res);

        String expression = "10*2-5";
        double result = calculator.calculate(expression);

        assertEquals(res, result);
        System.out.println(result);
    }
}