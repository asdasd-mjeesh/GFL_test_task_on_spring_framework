package com.example.gfl_test_task_on_spring.controller;

import com.example.gfl_test_task_on_spring.dto.ExpressionDto;
import com.example.gfl_test_task_on_spring.dto.ExpressionFilter;
import com.example.gfl_test_task_on_spring.entity.Expression;
import com.example.gfl_test_task_on_spring.exception.ExpressionAlreadyExistException;
import com.example.gfl_test_task_on_spring.mapper.ExpressionMapper;
import com.example.gfl_test_task_on_spring.service.ExpressionService;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/expressions")
public class ExpressionControllerV1 {

    private final ExpressionService expressionService;
    private final ExpressionMapper expressionMapper;

    @Autowired
    public ExpressionControllerV1(ExpressionService expressionService, ExpressionMapper expressionMapper) {
        this.expressionService = expressionService;
        this.expressionMapper = expressionMapper;
    }

    @PostMapping("")
    public String save(@RequestParam(name = "value") String expressionValue) {
        try {
            expressionService.save(expressionValue);
        } catch (Exception e) {
            throw new ExpressionAlreadyExistException(e.getMessage());
        }
        return "redirect:expressions/1";
    }

    @GetMapping("/new")
    public String newExpression(Model model) {
        model.addAttribute("expression", new Expression());
        return "expression-save";
    }

    @GetMapping("/{pageNo}")
    public String findAllOnPage(@PathVariable Integer pageNo,
                          Model model) {
        List<Expression> expressions = expressionService.findAllOnPage(pageNo - 1);
        model.addAttribute("expressions", expressionMapper.map(expressions));
        model.addAttribute("pageNo", pageNo);

        return "expression-main-page";
    }

    @GetMapping("/params/{pageNo}")
    public String findByFilter(@PathVariable(name = "pageNo") Integer pageNo,
                               @RequestParam(name = "equalsParam", defaultValue = "") Double equals,
                               @RequestParam(name = "minParam", defaultValue = "") Double min,
                               @RequestParam(name = "maxParam", defaultValue = "") Double max,
                               Model model) {

        ExpressionFilter filter = new ExpressionFilter(equals, min, max, pageNo - 1);
        List<Expression> expressions = expressionService.findAllByFilter(filter);

        model.addAttribute("expressions", expressions);
        model.addAttribute("pageNo", pageNo);

        return "expression-main-page";
    }

    @GetMapping("/edit/{id}")
    public String editExpression(@PathVariable("id") Long id,
                                 Model model) {
        Expression expression = expressionService.findById(id).orElse(new Expression());
        ExpressionDto expressionDto = expressionMapper.map(expression);
        model.addAttribute("expression", expressionDto);

        return "expression-edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable(name = "id") Long id,
                         @RequestParam(name = "newValue") String newValue) {
        Expression expression = new Expression(id, newValue);
        expressionService.update(expression);

        return "redirect:/api/v1/expressions/1";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        expressionService.delete(id);
        return "redirect:/api/v1/expressions/1";
    }
}
