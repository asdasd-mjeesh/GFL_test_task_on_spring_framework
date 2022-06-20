package com.example.gfl_test_task_on_spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Expression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "result")
    private Double result;

    public Expression(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Expression(String value, Double result) {
        this.value = value;
        this.result = result;
    }
}
