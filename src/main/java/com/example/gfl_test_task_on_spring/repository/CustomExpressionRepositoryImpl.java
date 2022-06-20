package com.example.gfl_test_task_on_spring.repository;

import com.example.gfl_test_task_on_spring.dto.ExpressionFilter;
import com.example.gfl_test_task_on_spring.entity.Expression;
import org.hibernate.Session;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class CustomExpressionRepositoryImpl implements CustomExpressionRepository {

    @PersistenceContext
    private Session session;

    @Override
    public List<Expression> findAllByFilter(ExpressionFilter filter) {
        var cb = session.getCriteriaBuilder();
        var criteria = cb.createQuery(Expression.class);
        var expression = criteria.from(Expression.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getEquals() != null) {
            predicates.add(cb.equal(expression.get("result"), filter.getEquals()));
        }
        if (filter.getMin() != null) {
            predicates.add(cb.greaterThan(expression.get("result"), filter.getMin()));
        }
        if (filter.getMax() != null) {
            predicates.add(cb.lessThan(expression.get("result"), filter.getMax()));
        }

        criteria.select(expression)
                .where(predicates.toArray(Predicate[]::new))
                .orderBy(cb.asc(expression.get("result")));

        return session.createQuery(criteria)
                .setMaxResults(filter.getPageSize())
                .setFirstResult(filter.getCurrentPage())
                .getResultList();
    }
}
