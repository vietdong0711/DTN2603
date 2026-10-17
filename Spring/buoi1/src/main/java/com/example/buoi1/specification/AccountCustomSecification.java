package com.example.buoi1.specification;

import com.example.buoi1.entity.Account;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;


@AllArgsConstructor
public class AccountCustomSecification implements Specification<Account> {
    private String field;
    private Object value;// string, integer, date .. đều là con của Object

    @Override
    public @Nullable Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if ("username".equalsIgnoreCase(field)) {
             return criteriaBuilder.like(root.get("username"), "%"+value.toString()+"%");
                    // username  like %value%
        }

        if ("email".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("email"), "%"+value.toString()+"%");
            // username  like %value%
        }

        if ("depName".equalsIgnoreCase(field)) {
            return criteriaBuilder.like(root.get("department").get("name"), "%"+value.toString()+"%");
            // dep.department_name like %value%
        }
        return null;
    }
}
