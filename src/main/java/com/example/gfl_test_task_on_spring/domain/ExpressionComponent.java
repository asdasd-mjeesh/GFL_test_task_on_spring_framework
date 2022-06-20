package com.example.gfl_test_task_on_spring.domain;

public class ExpressionComponent {

    private final StringBuilder value;
    private final int priority;
    private boolean isDigit;

    public ExpressionComponent(Double digit) {
        this.isDigit = true;
        this.priority = 0;
        this.value = new StringBuilder(String.valueOf(digit));
    }

    public ExpressionComponent(char symbol) {
        this.value = new StringBuilder(String.valueOf(symbol));
        this.isDigit = Character.isDigit(symbol);

        if (symbol == '(') {
            priority = 1;
        } else if (symbol == '-' || symbol == '+') {
            priority = 2;
        } else if (symbol == '*' || symbol == '/') {
            priority = 3;
        } else  {
            priority = 0;
        }
    }

    @Override
    public String toString() {
        return "ExpressionComponent{" +
               "value=" + value +
               ", priority=" + priority +
               ", isDigit=" + isDigit +
               '}';
    }

    public void append(char symbol) {
        this.isDigit = true;
        value.append(symbol);
    }

    public boolean isDigit() {
        return isDigit;
    }

    public String value() {
        return value.toString();
    }

    public int priority() {
        return priority;
    }
}
